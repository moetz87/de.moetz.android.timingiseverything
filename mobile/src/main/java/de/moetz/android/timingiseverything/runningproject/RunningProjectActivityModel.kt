package de.moetz.android.timingiseverything.runningproject

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.os.AsyncTask
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.project.Project

class RunningProjectActivityModel : BaseObservable() {

    @Bindable
    var runningProject: RunningProject? = null
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var projects: ObservableArrayList<Project>? = null
        set(value) {
            field = value
            notifyChange()
        }

    @Bindable
    var selectedProject: Project? = null
        set(value) {
            field = value
            notifyChange()
        }

    init {
        AsyncTask.execute({
            this.runningProject = AppDatabase.get().runningProjectDao().read()
            this.projects = ObservableArrayList()
            this.projects!!.addAll(AppDatabase.get().projectDao().get())
            notifyChange()
        })
    }


}