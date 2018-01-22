package de.moetz.android.timingiseverything.timereg

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.os.AsyncTask
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.view.model.ViewModel

class TimeRegsActivityModel : ViewModel() {

    @Bindable
    var timeregs: ObservableArrayList<TimeRegistration>? = null

    init {
        AsyncTask.execute({
            this.timeregs = ObservableArrayList()
            this.timeregs!!.addAll(AppDatabase.get().timeregDao().get())
        })
    }

}