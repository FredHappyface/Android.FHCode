package com.fredhappyface.fhcode

import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

/**
 * ActivityThemable class inherits from the AppCompatActivity class - AppCompatActivity with custom
 * themes. Overrides onCreate and onResume to set the theme
 */
open class ActivityThemable : AppCompatActivity() {
	internal lateinit var sharedPreferences: SharedPreferences
	internal var currentTheme = 0

	/**
	 * Triggered when the activity is created. Sets the theme to one that the user selected
	 * @param savedInstanceState activity saved data
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
		this.currentTheme = this.sharedPreferences.getInt("theme", 3)
		when (this.currentTheme) {
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
		val theme = this.sharedPreferences.getInt("theme", 3)
		if (this.currentTheme != theme) {
			this.currentTheme = theme
			recreate()
		}
	}
}
