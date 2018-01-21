package de.moetz.android.timingiseverything.database

import android.arch.persistence.room.TypeConverter
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime

class Converters {

    @TypeConverter
    fun stringToLocalDate(value: String?): LocalDate? {
        return if (value == null) null else LocalDate.parse(value)
    }

    @TypeConverter
    fun localDateToString(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun stringToLocalDateTime(value: String?): LocalDateTime? {
        return if (value == null) null else LocalDateTime.parse(value)
    }

    @TypeConverter
    fun localDateTimeToString(date: LocalDateTime?): String? {
        return date?.toString()
    }

}
