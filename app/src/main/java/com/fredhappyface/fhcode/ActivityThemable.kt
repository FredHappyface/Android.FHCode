package com.fredhappyface.fhcode

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager


open class ActivityThemable: AppCompatActivity() {
    open lateinit var sharedPreferences: SharedPreferences
    var currentTheme = 0

    /**
     * Triggered when the activity is created, sets the title to BlackC4t with a color of #ABB2BF. Sets the theme to one
     * that the user selected
     * @param savedInstanceState activity saved data
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        currentTheme = sharedPreferences.getInt("theme", 3)
        when (currentTheme) {
            0 -> setTheme(R.style.LightTheme)
            1 -> setTheme(R.style.DarkTheme)
            2 -> setTheme(R.style.BlackTheme)
            else -> when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_YES -> setTheme(R.style.DarkTheme)
                Configuration.UI_MODE_NIGHT_NO -> setTheme(R.style.LightTheme)
            }
        }
    }

    /**
     * Triggered when an activity is resumed. If the theme differs from the currently active theme,
     * then the activity is recreated
     */
    override fun onResume() {
        super.onResume()
        val theme = sharedPreferences.getInt("theme", 3)
        if (currentTheme != theme) {
            currentTheme = theme
            recreate()
        }
    }
}