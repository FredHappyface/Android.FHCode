package com.fredhappyface.fhcode

import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.CharacterStyle
import android.widget.EditText
import kotlinx.coroutines.*
import java.util.*

/**
 * TextHighlight provides convenience functions to apply colours to an edit text
 *
 * >
var colours: Colours = ColoursDark()
var languageRules: LanguageRules = LanguageRulesJava()
val codeEditText: EditText = findViewById(R.id.codeHighlight)
val textHighlight = TextHighlight(
codeEditText,
languageRules,
colours
)
textHighlight.start()
 *
 * @constructor
 * @param targetEditText EditText
 * @param languageRules LanguageRules
 * @param colours Colours
 * @param timeDelay Long
 */
open class TextHighlight(
	targetEditText: EditText,
	languageRules: LanguageRules,
	colours: Colours,
	timeDelay: Long = 150
) {
	/**
	 * Update the text
	 */
	private val textWatch = object : TextWatcher {
		override fun afterTextChanged(s: Editable?) {
		}

		override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			highlightTask?.cancel("Text changed")
			highlightTask = null
		}

		override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
		}
	}

	/**
	 * Refresh the highlighting every delay if input
	 */
	private val delayedTextWatch = DelayedTextWatch(
		timeDelay,
		action = {
			refreshHighlight()
		})

	/**
	 * targetEditText.text is the editable text
	 */
	private val editable: Editable
		get() = targetEditText.text

	/**
	 * targetEditText setter: clearAppliedStyles, set the value to targetEditText and refreshHighlight
	 */
	private var targetEditText: EditText = targetEditText
		set(value) {
			clearAppliedStyles()
			field = value
			refreshHighlight()
		}

	/**
	 * SpannableHighlighter setter: clearAppliedStyles, set the value to targetEditText and refreshHighlight
	 */
	private var syntaxHighlighter = SpannableHighlighter(languageRules, colours)
		set(value) {
			clearAppliedStyles()
			field = value
			refreshHighlight()
		}

	/**
	 * current highlightTask
	 */
	private var highlightTask: Job? = null

	/**
	 * Logic for refreshHighlight. Cancel existing highlightTask jobs and create a new CoroutineScope
	 * for highlightTask
	 *
	 */
	private fun refreshHighlight() {
		highlightTask?.cancel("Refresh Highlight")
		highlightTask = null
		highlightTask = CoroutineScope(Dispatchers.Main).launch {
			val currentText = editable.toString()
			val highlightEntities = withContext(Dispatchers.Default) {
				syntaxHighlighter.createHighlighting(currentText)
			}
			val original = syntaxHighlighter.appliedStyles
			val previousStyles = mutableSetOf<CharacterStyle>().apply { addAll(original) }
			try {
				syntaxHighlighter.highlight(editable, highlightEntities)
			} catch (e: IndexOutOfBoundsException) {
				clearAppliedStyles()
			}
			removeStyles(previousStyles)
		}
	}

	/**
	 * Call to syntaxHighlighter.clearAppliedStyles(editable)
	 *
	 */
	private fun clearAppliedStyles(): Unit = syntaxHighlighter.clearAppliedStyles(editable)

	/**
	 * Remove styles from editable (targetEditText.text) and remove styles from syntaxHighlighter.appliedStyles
	 *
	 * @param styles
	 */
	private fun removeStyles(styles: Set<CharacterStyle>) {
		styles.forEach {
			editable.removeSpan(it)
			syntaxHighlighter.appliedStyles.remove(it)
		}
	}

	/**
	 * Start the TextHighlight functionality as applies to targetEditText
	 *
	 */
	open fun start() {
		targetEditText.addTextChangedListener(delayedTextWatch)
		targetEditText.addTextChangedListener(textWatch)
	}

	/**
	 * Stop/end the TextHighlight functionality as applies to targetEditText
	 *
	 */
	open fun end() {
		targetEditText.removeTextChangedListener(textWatch)
		targetEditText.removeTextChangedListener(delayedTextWatch)
	}
}

/**
 * DelayedTextWatch. Override the onTextChanged function to apply some action after a timer
 *
 * > delayedTextWatch = DelayedTextWatch(
100,
action = {
refreshHighlight()
})
 *
 * @property timeDelay Long. default=150
 * @property action (CharSequence?) -> Unit: some callable
 */
private class DelayedTextWatch(
	private var timeDelay: Long = 150,
	val action: (CharSequence?) -> Unit
) : TextWatcher {
	private var timer = Timer()
	override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
		val text = s?.toString() ?: ""
		timer.cancel()
		timer = Timer()
		timer.schedule(object : TimerTask() {
			override fun run() {
				CoroutineScope(Dispatchers.Main).launch {
					action(text)
				}
			}
		}, timeDelay)
	}

	override fun afterTextChanged(s: Editable?): Unit = Unit
	override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int): Unit =
		Unit
}

/**
 * SpannableHighlighter class used to apply ruleMatches: List<RuleMatch> to a spannable: Spannable
 * using specific languageRules and colours
 */
open class SpannableHighlighter(
	private val languageRules: LanguageRules,
	private val colours: Colours
) : LanguageRules by languageRules {
	/**
	 * appliedStyles: MutableSet<CharacterStyle> tracks styles for a Spannable
	 */
	open val appliedStyles: MutableSet<CharacterStyle> = mutableSetOf()

	/**
	 * clearAppliedStyles. Remove styles from a Spannable and clear the appliedStyles
	 *
	 * @param spannable Spannable
	 */
	internal fun clearAppliedStyles(spannable: Spannable) {
		appliedStyles.forEach {
			spannable.removeSpan(it)
		}
		appliedStyles.clear()
	}

	/**
	 * Apply some character style (eg ForegroundColorSpan) to a Spannable from a start index to
	 * an end index
	 *
	 * @param characterStyle CharacterStyle
	 * @param spannable Spannable
	 * @param start Int
	 * @param end Int
	 */
	private fun applyStyle(
		characterStyle: CharacterStyle,
		spannable: Spannable,
		start: Int,
		end: Int
	) {
		spannable.setSpan(characterStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
		appliedStyles.add(characterStyle)
	}

	/**
	 * highlight a Spannable with a list of RuleMatches
	 *
	 * @param spannable Spannable
	 * @param ruleMatches List<RuleMatch>
	 */
	internal suspend fun highlight(spannable: Spannable, ruleMatches: List<RuleMatch>) {
		coroutineScope {
			ruleMatches.map {
				async {
					val characterStyle = colours.getColour(it.ruleName)
					applyStyle(characterStyle, spannable, it.startIndex, it.endIndex)
				}
			}.awaitAll()
		}
	}
}
