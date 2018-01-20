package de.moetz.android.timingiseverything.view

import android.widget.Toast
import de.moetz.android.timingiseverything.ApplicationContext

abstract class ViewElement {

    abstract fun validate(): Boolean

    protected fun showMessage(msg: String) {
        Toast.makeText(ApplicationContext.context, msg, Toast.LENGTH_SHORT).show()
    }

}