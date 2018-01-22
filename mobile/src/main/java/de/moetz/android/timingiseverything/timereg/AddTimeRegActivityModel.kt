package de.moetz.android.timingiseverything.timereg

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.os.AsyncTask
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.project.Project
import de.moetz.android.timingiseverything.view.model.ViewModel

class AddTimeRegActivityModel : ViewModel() {

    @Bindable
    var timereg = TimeRegistration.default()
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var projects = ObservableArrayList<Project>()
        set(value) {
            field = value
            notifyChange()
        }

    init {
        AsyncTask.execute({
            this.projects!!.addAll(AppDatabase.get().projectDao().get())
            notifyChange()
        })
    }

    fun validateTimereg(): Boolean {
        var valid = true
        if (this.timereg == null) {
            showMessage("Unbekannter Validierungsfehler")
            valid = false
        }
        if (this.timereg!!.project == null) {
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