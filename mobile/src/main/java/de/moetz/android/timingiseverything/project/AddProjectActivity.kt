package de.moetz.android.timingiseverything.project

import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R
import de.moetz.android.timingiseverything.database.AppDatabase
import de.moetz.android.timingiseverything.runningproject.RunningProjectActivity

class AddProjectActivity : BaseActivity("Projekt hinzufügen") {

    var project = Project.default()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_add)
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.addproject, this.project)

    }

    fun onAddProjectClick(view: View) {
        if (this.project.validate()) {
            AsyncTask.execute({
                AppDatabase.get().projectDao().insert(this.project)
                startActivity(Intent(this, RunningProjectActivity::class.java))
            })
        }
    }

}
