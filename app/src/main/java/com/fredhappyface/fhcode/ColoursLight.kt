package com.fredhappyface.fhcode

import android.graphics.Color
import android.text.style.ForegroundColorSpan

class ColoursLight : Colours {

    override fun getColour(type: String): ForegroundColorSpan {
        val colourMap = mapOf(
            "keyword" to "#9E25A6",
            "annotation" to "#BF1243",
            "constant" to "#BA8400",
            "comment" to "#383A42",
            "class" to "#5DA14C",
            "import" to "#5077F4"
        )
        return ForegroundColorSpan(Color.parseColor(colourMap[type]))
    }
}