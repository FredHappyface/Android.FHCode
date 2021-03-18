package com.fredhappyface.fhcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText

class ActivityMain : AppCompatActivity() {

    // Keep track of ...
    // - the file name/ id
    // - file content

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

     override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_new_file -> {
                true
            }
            R.id.action_open -> {
                true
            }
            R.id.action_save -> {
                true
            }
            R.id.action_save_as -> {
                true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, ActivitySettings::class.java))
                true
            }
            R.id.action_about-> {
                startActivity(Intent(this, ActivityAbout::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}