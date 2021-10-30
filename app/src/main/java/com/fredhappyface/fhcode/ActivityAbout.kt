package com.fredhappyface.fhcode
import android.os.Bundle
/**
 * ActivityAbout class inherits from the ActivityThemable class - provides the about view
 */
class ActivityAbout : ActivityThemable() {
    /**
     * Override the onCreate method from ActivityThemable adding the activity_about view
     *
     * @param savedInstanceState saved state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }
}
