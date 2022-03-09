//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[DelayedTextWatch](index.md)

# DelayedTextWatch

[androidJvm]\
private class [DelayedTextWatch](index.md)(timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), action: ([CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) : [TextWatcher](https://developer.android.com/reference/kotlin/android/text/TextWatcher.html)

DelayedTextWatch. Override the onTextChanged function to apply some action after a timer

delayedTextWatch = DelayedTextWatch( 100, action = { refreshHighlight() })

## Constructors

| | |
|---|---|
| [DelayedTextWatch](-delayed-text-watch.md) | [androidJvm]<br>fun [DelayedTextWatch](-delayed-text-watch.md)(timeDelay: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 150, action: ([CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [afterTextChanged](after-text-changed.md) | [androidJvm]<br>open override fun [afterTextChanged](after-text-changed.md)(s: [Editable](https://developer.android.com/reference/kotlin/android/text/Editable.html)?) |
| [beforeTextChanged](before-text-changed.md) | [androidJvm]<br>open override fun [beforeTextChanged](before-text-changed.md)(s: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?, start: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), after: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |
| [onTextChanged](on-text-changed.md) | [androidJvm]<br>open override fun [onTextChanged](on-text-changed.md)(s: [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?, start: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), before: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), count: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) |

## Properties

| Name | Summary |
|---|---|
| [action](action.md) | [androidJvm]<br>val [action](action.md): ([CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html)?) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>(CharSequence?) -> Unit: some callable |
| [timeDelay](time-delay.md) | [androidJvm]<br>private var [timeDelay](time-delay.md): [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html) = 150<br>Long. default=150 |
| [timer](timer.md) | [androidJvm]<br>private var [timer](timer.md): [Timer](https://developer.android.com/reference/kotlin/java/util/Timer.html) |
