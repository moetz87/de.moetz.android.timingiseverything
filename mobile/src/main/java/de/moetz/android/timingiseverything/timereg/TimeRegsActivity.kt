package de.moetz.android.timingiseverything.timereg

import android.os.Bundle
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R

class TimeRegsActivity : BaseActivity("Zeiten-Management") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.timeregs)
    }

}
