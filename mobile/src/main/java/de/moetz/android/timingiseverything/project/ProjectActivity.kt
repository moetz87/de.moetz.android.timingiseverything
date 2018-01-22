package de.moetz.android.timingiseverything.project

import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R

class ProjectActivity : BaseActivity("Projekte") {

    var model = ProjectActivityModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects)
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.model, this.model)
    }

    fun onAddProjectClick(view: View) {
        startActivity(Intent(this, AddProjectActivity::class.java))

    }

}
