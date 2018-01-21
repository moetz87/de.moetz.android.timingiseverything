package de.moetz.android.timingiseverything.view.model

import android.widget.Toast
import de.moetz.android.timingiseverything.ApplicationContext

abstract class ViewModel {

    abstract fun validate(): Boolean

    protected fun showMessage(msg: String) {
        Toast.makeText(ApplicationContext.context, msg, Toast.LENGTH_SHORT).show()
    }

}