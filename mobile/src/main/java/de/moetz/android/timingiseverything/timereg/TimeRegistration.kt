package de.moetz.android.timingiseverything.timereg

import java.util.*

data class TimeRegistration(val acronym: String,
                            val date: Date,
                            val project: String,
                            val time: Double,
                            val remarks: String)