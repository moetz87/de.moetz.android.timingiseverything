package de.moetz.android.timingiseverything.runningproject

import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.timereg.TimeRegistration
import de.moetz.android.timingiseverything.view.model.ObjectHolder
import de.moetz.android.timingiseverything.view.model.Observable
import de.moetz.android.timingiseverything.view.model.StringHolder
import org.joda.time.LocalDateTime
import org.joda.time.Period
import java.util.*

class RunningProjectActivity : BaseActivity("Laufendes Projekt") {

    private var runningProject = Observable<RunningProject>()
    private var project = StringHolder("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.runningproject)

        AsyncTask.execute({
            this.runningProject.set(AppDatabase.get().runningProjectDao().read())
            // this.runningProject.set(RunningProject("QES"))
        })
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.runningproject, this.runningProject)
        binding.setVariable(BR.project, this.project)
    }

    fun onStartClick(view: View) {
        Log.d("RunningProjectActivity", "project: ${project}")
        if (this.runningProject.isSet()) {
            saveAsTimeRegistration(this.runningProject.get()!!)
        }
        val newRunningProject = RunningProject(this.project.value)
        this.runningProject.set(newRunningProject)
        AsyncTask.execute({
            AppDatabase.get().runningProjectDao().save(newRunningProject)
        })
    }

    fun onStopClick(view: View) {
        Log.d("RunningProjectActivity", "project: ${project}")
        saveAsTimeRegistration(this.runningProject.get()!!)
        this.runningProject.unset()
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
