package com.fredhappyface.fhcode

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

import kotlinx.coroutines.*

class TextHighlight(
    targetEditText: EditText,
    languageRules: LanguageRules,
    colours: Colours,
    timeDelay: Int=150
) {
    private var highlightTask: Job?=null

    private val realtimeTextWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            highlightTask?.cancel("Text has changed")
            highlightTask = null
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }
}