package de.moetz.android.timingiseverything.timereg

import android.databinding.Bindable
import de.moetz.android.timingiseverything.view.model.ViewModel

class AddTimeRegActivityModel : ViewModel() {

    @Bindable
    var timereg: TimeRegistration? = null
        set(value) {
            field = value
            notifyChange()
        }

    init {
        this.timereg = TimeRegistration.default()
        notifyChange()
    }

    fun validateTimereg(): Boolean {
        var valid = true
        if (this.timereg == null) {
            showMessage("Unbekannter Validierungsfehler")
            valid = false
        }
        if (this.timereg!!.project.isNullOrBlank()) {
            showMessage("Projekt ist nicht valide")
            valid = false
        }
        if (this.timereg!!.time < 0) {
            showMessage("Zeit ist nicht valide")
            valid = false
        }
        return valid
    }

}