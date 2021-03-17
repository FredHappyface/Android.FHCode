package com.fredhappyface.fhcode

import android.graphics.Color
import android.text.style.ForegroundColorSpan

class ColoursLight: Colours{

    override fun getColour(type: String): ForegroundColorSpan {
        val colourMap = mapOf("keyword" to "#333333")
        return ForegroundColorSpan(Color.parseColor(colourMap[type]))
    }
}