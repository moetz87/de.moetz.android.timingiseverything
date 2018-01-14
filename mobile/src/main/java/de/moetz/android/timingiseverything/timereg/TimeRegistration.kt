package de.moetz.android.timingiseverything.timereg

import android.arch.persistence.room.*
import java.util.*


@Entity
data class TimeRegistration(val acronym: String,
                            val date: Date,
                            val project: String,
                            val time: Double) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var remarks: String = ""

}


@Dao
interface TimeRegistrationDao {

    @Query("SELECT * FROM TimeRegistration")
    fun get(): List<TimeRegistration>

    @Query("SELECT * FROM TimeRegistration WHERE id=:id")
    fun get(id: Int): TimeRegistration

    @Insert
    fun insert(timereg: TimeRegistration): Long

    @Update
    fun update(timereg: TimeRegistration)

    @Delete
    fun delete(timereg: TimeRegistration)

    @Query("DELETE FROM TimeRegistration")
    fun delete()

}