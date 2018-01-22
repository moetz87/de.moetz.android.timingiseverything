package de.moetz.android.timingiseverything.runningproject

import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.timereg.TimeRegistration
import org.joda.time.LocalDateTime
import org.joda.time.Period
import java.util.*

class RunningProjectActivity : BaseActivity("Laufendes Projekt") {

    var model = RunningProjectActivityModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.runningproject)
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.model, this.model)
    }

    fun onStartClick(view: View) {
        if (this.model.runningProject != null) {
            saveAsTimeRegistration(this.model.runningProject!!)
        }
        val newRunningProject = RunningProject(this.model.selectedProject!!)
        this.model.runningProject = newRunningProject
        AsyncTask.execute({
            AppDatabase.get().runningProjectDao().save(newRunningProject)
        })
    }

    fun onStopClick(view: View) {
        saveAsTimeRegistration(this.model.runningProject!!)
        this.model.runningProject = null
        AsyncTask.execute({
            AppDatabase.get().runningProjectDao().delete()
        })
    }

    private fun saveAsTimeRegistration(runningProject: RunningProject) {
        val start = runningProject.start!!
        val end = LocalDateTime()
        val project = runningProject.project!!
        val time = calculateTime(start, end)
        val timereg = TimeRegistration(start.toLocalDate(), project, time, "")
        AsyncTask.execute({
            AppDatabase.get().timeregDao().insert(timereg)
        })
    }

    private fun calculateTime(start: LocalDateTime, end: LocalDateTime): Double {
        val period = Period.fieldDifference(start, end)
        val hours = period.toStandardHours().hours
        val minutes = period.toStandardMinutes().minutes / 60.0
        // return (hours + minutes)
        return Random().nextDouble() * 6.0
    }

}
