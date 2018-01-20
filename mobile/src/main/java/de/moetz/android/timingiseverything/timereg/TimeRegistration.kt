package de.moetz.android.timingiseverything.timereg

import android.arch.persistence.room.*
import de.moetz.android.timingiseverything.view.ListViewItem
import org.joda.time.LocalDate


@Entity
data class TimeRegistration(var date: LocalDate,
                            var project: String,
                            var time: Double,
                            var remarks: String) : ListViewItem() {

    companion object {
        fun default(): TimeRegistration {
            return TimeRegistration(LocalDate.now(), "", 0.0, "")
        }
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var acronym: String = "MOE"

    override fun validate(): Boolean {
        var valid = true
        if (project.isNullOrBlank()) {
            showMessage("Projekt ist nicht valide")
            valid = false
        }
        if (time < 0) {
            showMessage("Zeit ist nicht valide")
            valid = false
        }
        return valid
    }

    override fun getId(): Long {
        return this.id.toLong()
    }

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
