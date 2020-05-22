package com.app.gastranetwork.data.firebase.repository.app_info

import com.app.gastranetwork.BuildConfig
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AppInfo(
    var is_maintenance: Boolean? = false,
    var latest_version: String? = BuildConfig.VERSION_NAME
)