package com.app.gastranetwork.module.order_form_contact

import android.app.Activity
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.module.order_form_service.OrderFormServiceFragment

class OrderFormContractRouter(var activity: Activity?) : OrderFormContractContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToOrderFormService() {
        (activity as BaseActivity).changeFragment((activity as BaseActivity).supportFragmentManager, "OrderFormServiceFragment", OrderFormServiceFragment())
    }
}
