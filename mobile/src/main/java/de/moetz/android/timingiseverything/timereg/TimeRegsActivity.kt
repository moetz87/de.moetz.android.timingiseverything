package de.moetz.android.timingiseverything.timereg

import android.content.Intent
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.View
import de.moetz.android.timingiseverything.BR
import de.moetz.android.timingiseverything.BaseActivity
import de.moetz.android.timingiseverything.R


class TimeRegsActivity : BaseActivity("Zeitenübersicht") {

    var model = TimeRegsActivityModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.timeregs)
    }

    override fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.model, this.model)
    }

    fun onAddTimeRegClick(view: View) {
        startActivity(Intent(this, AddTimeRegActivity::class.java))
    }

}
