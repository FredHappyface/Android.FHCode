//[app](../../../index.md)/[com.fredhappyface.fhcode](../index.md)/[ColoursLight](index.md)/[getColour](get-colour.md)

# getColour

[androidJvm]\
open override fun [getColour](get-colour.md)(type: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [ForegroundColorSpan](https://developer.android.com/reference/kotlin/android/text/style/ForegroundColorSpan.html)

Get a colour for a given type

eg colourMap = ("keyword" to "magenta", "annotation" to "red", "constant" to "#orange", "comment" to "grey", "class" to "green", "import" to "blue")

#### Return

ForegroundColorSpan ForegroundColorSpan(Color.parseColor(colourMap[type](get-colour.md))) -> "magenta" if type="keyword"

## Parameters

androidJvm

| | |
|---|---|
| type | String eg "keyword" |
