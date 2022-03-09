//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[SpannableHighlighter](index.md)

# SpannableHighlighter

[androidJvm]\
open class [SpannableHighlighter](index.md)(languageRules: [LanguageRules](../-language-rules/index.md), colours: [Colours](../-colours/index.md)) : [LanguageRules](../-language-rules/index.md)

SpannableHighlighter class used to apply ruleMatches: List<RuleMatch> to a spannable: Spannable using specific languageRules and colours

## Constructors

| | |
|---|---|
| [SpannableHighlighter](-spannable-highlighter.md) | [androidJvm]<br>fun [SpannableHighlighter](-spannable-highlighter.md)(languageRules: [LanguageRules](../-language-rules/index.md), colours: [Colours](../-colours/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [applyStyle](apply-style.md) | [androidJvm]<br>private fun [applyStyle](apply-style.md)(characterStyle: [CharacterStyle](https://developer.android.com/reference/kotlin/android/text/style/CharacterStyle.html), spannable: [Spannable](https://developer.android.com/reference/kotlin/android/text/Spannable.html), start: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), end: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Apply some character style (eg ForegroundColorSpan) to a Spannable from a start index to an end index |
| [clearAppliedStyles](clear-applied-styles.md) | [androidJvm]<br>internal fun [clearAppliedStyles](clear-applied-styles.md)(spannable: [Spannable](https://developer.android.com/reference/kotlin/android/text/Spannable.html))<br>clearAppliedStyles. Remove styles from a Spannable and clear the appliedStyles |
| [highlight](highlight.md) | [androidJvm]<br>internal suspend fun [highlight](highlight.md)(spannable: [Spannable](https://developer.android.com/reference/kotlin/android/text/Spannable.html), ruleMatches: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;)<br>highlight a Spannable with a list of RuleMatches |

## Properties

| Name | Summary |
|---|---|
| [appliedStyles](applied-styles.md) | [androidJvm]<br>open val [appliedStyles](applied-styles.md): [MutableSet](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-set/index.html)&lt;[CharacterStyle](https://developer.android.com/reference/kotlin/android/text/style/CharacterStyle.html)&gt;<br>appliedStyles: MutableSet<CharacterStyle> tracks styles for a Spannable |
| [colours](colours.md) | [androidJvm]<br>private val [colours](colours.md): [Colours](../-colours/index.md) |
| [languageRules](language-rules.md) | [androidJvm]<br>private val [languageRules](language-rules.md): [LanguageRules](../-language-rules/index.md) |
