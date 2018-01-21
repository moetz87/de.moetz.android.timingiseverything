package de.moetz.android.timingiseverything.timereg

import android.content.Intent
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase


class TimeRegsActivity : BaseActivity("Zeitenübersicht") {

    val timeregs = ObservableArrayList<TimeRegistration>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.timeregs)
        AsyncTask.execute({
            this.timeregs.addAll(AppDatabase.get().timeregDao().get())
        })
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.infos, this.timeregs)
    }

    fun onAddTimeRegClick(view: View) {
        startActivity(Intent(this, AddTimeRegActivity::class.java))
    }

}
