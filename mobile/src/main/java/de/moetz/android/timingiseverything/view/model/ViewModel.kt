package de.moetz.android.timingiseverything.view.model

import android.databinding.BaseObservable
import android.widget.Toast
import de.moetz.android.timingiseverything.ApplicationContext

abstract class ViewModel : BaseObservable() {

    protected fun showMessage(msg: String) = Toast.makeText(ApplicationContext.context, msg, Toast.LENGTH_SHORT).show()

}