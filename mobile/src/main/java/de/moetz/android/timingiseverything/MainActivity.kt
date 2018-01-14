package de.moetz.android.timingiseverything

import android.os.Bundle

class MainActivity : BaseActivity() {

    override val toolbarText: String get() = "Timing is Everything"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

}
