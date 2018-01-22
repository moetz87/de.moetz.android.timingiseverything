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

class AddProjectActivity : BaseActivity("Projekt hinzufügen") {

    var model = AddProjectActivityModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_add)
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.model, this.model)

    }

    fun onAddProjectClick(view: View) {
        if (this.model.validateProject()) {
            AsyncTask.execute({
                AppDatabase.get().projectDao().insert(this.model.project!!)
                startActivity(Intent(this, ProjectActivity::class.java))
            })
        }
    }

}
