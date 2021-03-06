package de.moetz.android.timingiseverything.project

import android.arch.persistence.room.*
import de.moetz.android.timingiseverything.view.list.ListViewItem

@Entity
data class Project(var name: String,
                   var displayableName: String) : ListViewItem() {

    companion object {
        fun default() = Project("", "")
    }

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun getId(): Long {
        return this.id.toLong()
    }

}


@Dao
interface ProjectDao {

    @Query("SELECT * FROM Project")
    fun get(): List<Project>

    @Query("SELECT * FROM Project WHERE id=:id")
    fun get(id: Int): Project

    @Insert
    fun insert(project: Project): Long

    @Update
    fun update(project: Project)

    @Delete
    fun delete(project: Project)

    @Query("DELETE FROM Project")
    fun delete()

}