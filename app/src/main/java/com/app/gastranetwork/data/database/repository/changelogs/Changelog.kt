package com.app.gastranetwork.data.database.repository.changelogs

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "changelog")
data class Changelog(
    @Expose
    @PrimaryKey
    var id: Long,

    @Expose
    @SerializedName("version")
    @ColumnInfo(name = "version")
    var version: String,

    @Expose
    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String,

    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createdAt: String?,

    @Expose
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    var updatedAt: String?

)
