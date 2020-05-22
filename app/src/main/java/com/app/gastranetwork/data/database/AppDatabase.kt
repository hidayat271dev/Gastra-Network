package com.app.gastranetwork.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.gastranetwork.data.database.repository.changelogs.Changelog
import com.app.gastranetwork.data.database.repository.changelogs.ChangelogDao
import com.app.gastranetwork.util.AppConstants

@Database(
    version = 1
    , entities = [
        (Changelog::class)
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun changelogDao(): ChangelogDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    AppConstants.APP_DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}