package de.moetz.android.timingiseverything.project

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

class ProjectActivity : BaseActivity("Projekte") {

    val projects = ObservableArrayList<Project>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects)
        AsyncTask.execute({
            this.projects.addAll(AppDatabase.get().projectDao().get())
        })
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.projects, this.projects)
    }

    fun onAddProjectClick(view: View) {
        startActivity(Intent(this, AddProjectActivity::class.java))

    }

}
