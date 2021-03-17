package com.fredhappyface.fhcode

import android.graphics.Color
import android.text.style.ForegroundColorSpan

class ColoursDark: Colours {

    override fun getColour(type: String): ForegroundColorSpan {
        val colourMap = mapOf("keyword" to "#660000")
        return ForegroundColorSpan(Color.parseColor(colourMap[type]))
    }
}