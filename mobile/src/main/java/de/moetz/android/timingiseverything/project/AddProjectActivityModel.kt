package de.moetz.android.timingiseverything.project

import android.databinding.Bindable
import de.moetz.android.timingiseverything.view.model.ViewModel

class AddProjectActivityModel : ViewModel() {

    @Bindable
    var project: Project? = null
        set(value) {
            field = value
            notifyChange()
        }

    init {
        this.project = Project.default()
        notifyChange()
    }

    fun validateProject(): Boolean {
        var valid = true
        if (this.project == null) {
            showMessage("Unbekannter Validierungsfehler")
            valid = false
        }
        if (this.project!!.name.isNullOrBlank()) {
            showMessage("Name ist nicht valide")
            valid = false
        }
        if (this.project!!.displayableName.isNullOrBlank()) {
            showMessage("DisplayableName ist nicht valide")
            valid = false
        }
        return valid
    }

}