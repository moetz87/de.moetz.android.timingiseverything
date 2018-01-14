package de.moetz.android.timingiseverything

import android.app.Application
import android.content.Context


class ApplicationContext : Application() {

    companion object {

        var instance: ApplicationContext? = null
            private set

        val context: Context
            get() = instance!!.applicationContext

    }

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.instance = this
    }

}