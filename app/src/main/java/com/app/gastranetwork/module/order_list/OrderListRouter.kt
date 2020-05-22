package com.app.gastranetwork.module.order_list

import android.app.Activity
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.module.order_form_corp.OrderFormCorpFragment

class OrderListRouter(var activity: Activity?) : OrderListContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToOrderFormCorp() {
        (activity as BaseActivity).changeFragment((activity as BaseActivity).supportFragmentManager, "OrderFormCorpFragment", OrderFormCorpFragment())
    }
}
