package de.moetz.android.timingiseverything.runningproject

import android.arch.persistence.room.*
import de.moetz.android.timingiseverything.project.Project
import org.joda.time.LocalDateTime

@Entity
class RunningProject(project: Project) {

    @PrimaryKey
    var id: Int = 1
    var start: LocalDateTime = LocalDateTime()
    @Embedded(prefix = "project")
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



