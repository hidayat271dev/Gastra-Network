package com.app.gastranetwork.data.firebase.repository.app_info

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

interface AppInfoListener {
    fun getDataAppInfo(data: Any)
}

object AppInfoRepository : ValueEventListener {

    var TAG = "AppInfoRepository"
    lateinit var listener: AppInfoListener
    var path_name = "app-info"

    override fun onCancelled(databaseError: DatabaseError) {
        Log.e(TAG, "load:onCancelled " + databaseError.message, databaseError.toException())
    }

    override fun onDataChange(dataSnapshot: DataSnapshot) {
        var data = dataSnapshot.value
        if (data != null) {
            listener.getDataAppInfo(data)
        }
    }

}