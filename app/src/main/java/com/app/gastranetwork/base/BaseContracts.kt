package com.app.gastranetwork.base

import android.content.Context
import android.os.Bundle

interface BaseContracts {

    interface View {
        fun getActivityContext(): Context?
    }

    interface Presenter {
        fun onCreate(bundle: Bundle? = null) {}
        fun onResume() {}
        fun onPause() {}
        fun onDestroy()
    }

    interface Interactor {
        fun unregister()
    }

    interface InteractorOutput

    interface Router {
        fun unregister()
    }

}
