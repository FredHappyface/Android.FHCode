package com.fredhappyface.fhcode

import android.text.style.ForegroundColorSpan

interface Colours {

    //("keyword" to "magenta", "annotation" to "red", "constant" to "#orange", "comment" to "grey", "class" to "green", "import" to "blue")
    fun getColour(type: String): ForegroundColorSpan
}