package com.app.gastranetwork.module.order_form_corp

import android.app.Activity
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.module.order_form_contact.OrderFormContractFragment

class OrderFormCorpRouter(var activity: Activity?) : OrderFormCorpContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToOrderFormContact() {
        (activity as BaseActivity).changeFragment((activity as BaseActivity).supportFragmentManager, "OrderFormContractFragment", OrderFormContractFragment())
    }

}
