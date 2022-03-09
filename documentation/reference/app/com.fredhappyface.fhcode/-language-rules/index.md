//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[LanguageRules](index.md)

# LanguageRules

[androidJvm]\
interface [LanguageRules](index.md)

interface LanguageRules implemented by LanguageRulesJava, LanguageRulesPython, LanguageRulesXML, ...

## Functions

| Name | Summary |
|---|---|
| [createHighlighting](create-highlighting.md) | [androidJvm]<br>open fun [createHighlighting](create-highlighting.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>Combine matching rules |
| [matchAnnotations](match-annotations.md) | [androidJvm]<br>abstract fun [matchAnnotations](match-annotations.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Annotations |
| [matchClasses](match-classes.md) | [androidJvm]<br>abstract fun [matchClasses](match-classes.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Classes |
| [matchComments](match-comments.md) | [androidJvm]<br>abstract fun [matchComments](match-comments.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Comments |
| [matchConstants](match-constants.md) | [androidJvm]<br>abstract fun [matchConstants](match-constants.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Constants |
| [matchImports](match-imports.md) | [androidJvm]<br>abstract fun [matchImports](match-imports.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Imports |
| [matchKeywords](match-keywords.md) | [androidJvm]<br>abstract fun [matchKeywords](match-keywords.md)(string: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[RuleMatch](../-rule-match/index.md)&gt;<br>match on Keywords |

## Inheritors

| Name |
|---|
| [LanguageRulesJava](../-language-rules-java/index.md) |
| [LanguageRulesPython](../-language-rules-python/index.md) |
| [LanguageRulesXML](../-language-rules-x-m-l/index.md) |
| [SpannableHighlighter](../-spannable-highlighter/index.md) |
