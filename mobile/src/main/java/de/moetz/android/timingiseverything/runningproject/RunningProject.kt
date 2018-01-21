package de.moetz.android.timingiseverything.runningproject

import android.arch.persistence.room.*
import org.joda.time.LocalDateTime

@Entity
class RunningProject(project: String) {

    @PrimaryKey
    var id: Int = 1
    var start: LocalDateTime = LocalDateTime()
    var project = project

}


@Dao
interface RunningProjectDao {

    @Query("SELECT * FROM RunningProject WHERE id=1")
    fun read(): RunningProject

    @Insert
    fun save(runningProject: RunningProject): Long

    @Query("DELETE FROM RunningProject")
    fun delete()

}


