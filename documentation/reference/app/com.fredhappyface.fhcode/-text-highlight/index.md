//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[TextHighlight](index.md)

# TextHighlight

[androidJvm]\
open class [TextHighlight](index.md)(targetEditText: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html), languageRules: [LanguageRules](../-language-rules/index.md), colours: [Colours](../-colours/index.md), timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html))

TextHighlight provides convenience functions to apply colours to an edit text

var colours: Colours = ColoursDark() var languageRules: LanguageRules = LanguageRulesJava() val codeEditText: EditText = findViewById(R.id.codeHighlight) val textHighlight = TextHighlight( codeEditText, languageRules, colours ) textHighlight.start()

## Parameters

androidJvm

| | |
|---|---|
| targetEditText | EditText |
| languageRules | LanguageRules |
| colours | Colours |
| timeDelay | Long |

## Constructors

| | |
|---|---|
| [TextHighlight](-text-highlight.md) | [androidJvm]<br>fun [TextHighlight](-text-highlight.md)(targetEditText: [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html), languageRules: [LanguageRules](../-language-rules/index.md), colours: [Colours](../-colours/index.md), timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 150) |

## Functions

| Name | Summary |
|---|---|
| [clearAppliedStyles](clear-applied-styles.md) | [androidJvm]<br>private fun [clearAppliedStyles](clear-applied-styles.md)()<br>Call to syntaxHighlighter.clearAppliedStyles(editable) |
| [end](end.md) | [androidJvm]<br>open fun [end](end.md)()<br>Stop/end the TextHighlight functionality as applies to targetEditText |
| [refreshHighlight](refresh-highlight.md) | [androidJvm]<br>private fun [refreshHighlight](refresh-highlight.md)()<br>Logic for refreshHighlight. Cancel existing highlightTask jobs and create a new CoroutineScope for highlightTask |
| [removeStyles](remove-styles.md) | [androidJvm]<br>private fun [removeStyles](remove-styles.md)(styles: [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[CharacterStyle](https://developer.android.com/reference/kotlin/android/text/style/CharacterStyle.html)&gt;)<br>Remove styles from editable (targetEditText.text) and remove styles from syntaxHighlighter.appliedStyles |
| [start](start.md) | [androidJvm]<br>open fun [start](start.md)()<br>Start the TextHighlight functionality as applies to targetEditText |

## Properties

| Name | Summary |
|---|---|
| [delayedTextWatch](delayed-text-watch.md) | [androidJvm]<br>private val [delayedTextWatch](delayed-text-watch.md): [DelayedTextWatch](../-delayed-text-watch/index.md)<br>Refresh the highlighting every delay if input |
| [editable](editable.md) | [androidJvm]<br>private val [editable](editable.md): [Editable](https://developer.android.com/reference/kotlin/android/text/Editable.html)<br>targetEditText.text is the editable text |
| [highlightTask](highlight-task.md) | [androidJvm]<br>private var [highlightTask](highlight-task.md): Job? = null<br>current highlightTask |
| [syntaxHighlighter](syntax-highlighter.md) | [androidJvm]<br>private var [syntaxHighlighter](syntax-highlighter.md): [SpannableHighlighter](../-spannable-highlighter/index.md)<br>SpannableHighlighter setter: clearAppliedStyles, set the value to targetEditText and refreshHighlight |
| [targetEditText](target-edit-text.md) | [androidJvm]<br>private var [targetEditText](target-edit-text.md): [EditText](https://developer.android.com/reference/kotlin/android/widget/EditText.html)<br>targetEditText setter: clearAppliedStyles, set the value to targetEditText and refreshHighlight |
| [textWatch](text-watch.md) | [androidJvm]<br>private val [textWatch](text-watch.md): TextHighlight.&lt;no name provided&gt;<br>Update the text |
