package com.app.gastranetwork.module.order_form_service

import android.app.Activity

class OrderFormServiceRouter(var activity: Activity?) : OrderFormServiceContracts.Router {

    override fun unregister() {
        activity = null
    }

}
