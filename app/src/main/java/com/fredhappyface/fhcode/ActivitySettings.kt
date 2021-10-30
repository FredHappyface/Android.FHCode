package com.fredhappyface.fhcode

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.preference.PreferenceManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider

/**
 * ActivitySettings class inherits from the ActivityThemable class - provides the settings view
 */
class ActivitySettings : ActivityThemable() {
	/**
	 * Override the onCreate method from ActivityThemable adding the activity_settings view and selecting
	 * the current theme
	 *
	 * @param savedInstanceState saved state
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_settings)
		val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
		// Theme apply border to selected
		val cardView = findViewById<LinearLayout>(R.id.theme).getChildAt(
			sharedPreferences.getInt("theme", 3)
		) as MaterialCardView
		cardView.strokeWidth = (3 * applicationContext.resources.displayMetrics.density).toInt()
		cardView.strokeColor = resources.getColor(R.color.magenta, theme)
		// Text save
		val textSlider = findViewById<Slider>(R.id.slider)
		textSlider.addOnChangeListener(Slider.OnChangeListener { _, value, _ ->
			val editor: Editor = sharedPreferences.edit()
			editor.putInt("text", value.toInt())
			editor.apply()
		})
		// Text apply
		val textSize = sharedPreferences.getInt("text", 18)
		textSlider.value = textSize.toFloat()
	}

	/**
	 * Compare view.id of the radio button selected to set the theme and recreate the activity
	 *
	 * @param view
	 */
	fun changeTheme(view: View) {
		val editor: Editor = sharedPreferences.edit()
		editor.putInt(
			"theme", when (view.id) {
				R.id.radioLight -> 0
				R.id.radioDark -> 1
				R.id.radioBlack -> 2
				else -> 3
			}
		)
		editor.apply()
		recreate()
	}
}
