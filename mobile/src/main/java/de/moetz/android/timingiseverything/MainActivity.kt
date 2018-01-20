package de.moetz.android.timingiseverything

import android.databinding.ViewDataBinding
import android.os.Bundle

class MainActivity : BaseActivity("Timing is Everything") {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun bindData(binding: ViewDataBinding) {}

}
