package com.app.gastranetwork.data.database.repository.changelogs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChangelogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(changelog: List<Changelog>)

    @Query("SELECT * FROM changelog")
    fun loadAll(): List<Changelog>
}