package de.moetz.android.timingiseverything.database

import android.arch.persistence.db.SupportSQLiteOpenHelper
import android.arch.persistence.room.*
import android.util.Log
import de.moetz.android.timingiseverything.ApplicationContext
import de.moetz.android.timingiseverything.project.Project
import de.moetz.android.timingiseverything.project.ProjectDao
import de.moetz.android.timingiseverything.runningproject.RunningProject
import de.moetz.android.timingiseverything.runningproject.RunningProjectDao
import de.moetz.android.timingiseverything.timereg.TimeRegistration
import de.moetz.android.timingiseverything.timereg.TimeRegistrationDao


@Database(entities = arrayOf(TimeRegistration::class, RunningProject::class, Project::class), version = 16)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun get(): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(ApplicationContext.context, AppDatabase::class.java, "appdb")
                        // .addMigrations(MIGRATION_1_2)
                        .fallbackToDestructiveMigration()
                        .build()
                Log.d("", "AppDatabase Instance created")
            }
            return instance!!
        }
    }

    abstract fun timeregDao(): TimeRegistrationDao

    abstract fun runningProjectDao(): RunningProjectDao

    abstract fun projectDao(): ProjectDao

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
