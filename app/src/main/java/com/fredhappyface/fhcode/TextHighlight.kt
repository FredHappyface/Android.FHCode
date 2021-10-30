package com.fredhappyface.fhcode
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.text.style.CharacterStyle
import android.widget.EditText
import kotlinx.coroutines.*
import java.util.*
open class TextHighlight(
    targetEditText: EditText,
    languageRules: LanguageRules,
    colours: Colours,
    timeDelay: Long = 150
) {
    private var syntaxHighlighter = SpannableHighlighter(languageRules, colours)
        set(value) {
            clearAppliedStyles()
            field = value
            refreshHighlight()
        }
    internal var highlightTask: Job? = null
    private val delayedTextWatch = DelayedTextWatch(
        timeDelay,
        action = {
            refreshHighlight()
        })
    private val editable: Editable
        get() = targetEditText.text
    private var targetEditText: EditText = targetEditText
        set(value) {
            clearAppliedStyles()
            field = value
            refreshHighlight()
        }
    open fun refreshHighlight() {
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
    open fun clearAppliedStyles(): Unit = syntaxHighlighter.clearAppliedStyles(editable)
    open fun removeStyles(styles: Set<CharacterStyle>) {
        styles.forEach {
            editable.removeSpan(it)
            syntaxHighlighter.appliedStyles.remove(it)
        }
    }
    open fun start() {
        targetEditText.addTextChangedListener(delayedTextWatch)
        targetEditText.addTextChangedListener(textWatch)
    }
    open fun end() {
        targetEditText.removeTextChangedListener(textWatch)
        targetEditText.removeTextChangedListener(delayedTextWatch)
    }
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
}
open class DelayedTextWatch(
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
    open fun applyStyle(
        characterStyle: CharacterStyle,
        spannable: Spannable,
        start: Int,
        end: Int
    ) {
        spannable.setSpan(characterStyle, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        appliedStyles.add(characterStyle)
    }
    open fun clearAppliedStyles(spannable: Spannable) {
        appliedStyles.forEach {
            spannable.removeSpan(it)
        }
        appliedStyles.clear()
    }
    open suspend fun highlight(spannable: Spannable, ruleMatches: List<RuleMatch>) {
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
