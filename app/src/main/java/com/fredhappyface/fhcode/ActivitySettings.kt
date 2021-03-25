package com.fredhappyface.fhcode

import android.content.SharedPreferences.Editor
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.preference.PreferenceManager
import com.google.android.material.card.MaterialCardView


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
        val themeChoices = findViewById<LinearLayout>(R.id.theme)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val currentTheme = sharedPreferences.getInt("theme", 3)
        val cardView = themeChoices.getChildAt(currentTheme) as MaterialCardView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardView.strokeWidth = (3 * applicationContext.resources.displayMetrics.density).toInt()
            cardView.strokeColor = resources.getColor(R.color.magenta, theme)
        }
    }

    fun changeTheme(view: View) {
        var idx = 3
        when (view.id) {
            R.id.radioLight -> idx = 0
            R.id.radioDark -> idx = 1
            R.id.radioBlack -> idx = 2
        }
        val editor: Editor = sharedPreferences.edit()
        editor.putInt("theme", idx)
        editor.apply()
        recreate()
    }


}