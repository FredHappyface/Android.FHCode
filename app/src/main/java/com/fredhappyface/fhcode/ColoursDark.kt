package com.fredhappyface.fhcode

import android.graphics.Color
import android.text.style.ForegroundColorSpan

class ColoursDark : Colours {

    override fun getColour(type: String): ForegroundColorSpan {
        val colourMap = mapOf(
            "keyword" to "#C077DF",
            "annotation" to "#D66C75",
            "constant" to "#CB9A64",
            "comment" to "#555862",
            "class" to "#9DC376",
            "import" to "#68B6C2"
        )
        return ForegroundColorSpan(Color.parseColor(colourMap[type]))
    }
}