package com.app.gastranetwork.data.firebase

import com.app.gastranetwork.util.AppConstants
import com.google.firebase.database.FirebaseDatabase

class AppFirebase {

    var firebaseDatabase = FirebaseDatabase.getInstance().getReference(AppConstants.APP_FIREBASE_NAME)

    companion object {
        var instance = AppFirebase()
    }

}