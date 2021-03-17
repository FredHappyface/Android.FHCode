package com.fredhappyface.fhcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val codeEditText: EditText = findViewById(R.id.codeHighlight)
        val textHighlight = TextHighlight(
                codeEditText,
                LanguageRulesJava(),
                ColoursDark()
        )

        textHighlight.start()
        codeEditText.setText("void bar")
    }
}