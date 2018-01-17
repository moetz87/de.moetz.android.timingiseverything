package de.moetz.android.timingiseverything.database

import android.arch.persistence.room.TypeConverter
import org.joda.time.LocalDate

class Converters {

    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return if (value == null) null else LocalDate.parse(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString()
    }

}
