package de.moetz.android.timingiseverything.timereg

import android.content.Intent
import android.databinding.ObservableArrayList
import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
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
        findViewById<FloatingActionButton>(R.id.add_timereg_button).setOnClickListener {
            startActivity(Intent(this, AddTimeRegActivity::class.java))
        }
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.infos, this.timeregs)
    }

}
