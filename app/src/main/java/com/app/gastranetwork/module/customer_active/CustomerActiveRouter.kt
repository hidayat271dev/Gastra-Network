package com.app.gastranetwork.module.customer_active

import android.app.Activity

class CustomerActiveRouter(var activity: Activity?) : CustomerActiveContracts.Router {

    override fun unregister() {
        activity = null
    }

}
