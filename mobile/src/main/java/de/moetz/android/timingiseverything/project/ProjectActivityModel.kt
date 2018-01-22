package de.moetz.android.timingiseverything.project

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.os.AsyncTask
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.view.model.ViewModel

class ProjectActivityModel : ViewModel() {

    @Bindable
    var projects: ObservableArrayList<Project>? = null
        set(value) {
            field = value
            notifyChange()
        }

    init {
        AsyncTask.execute({
            this.projects = ObservableArrayList()
            this.projects!!.addAll(AppDatabase.get().projectDao().get())
            notifyChange()
        })
    }

}