package com.app.gastranetwork.module.order_list

import android.app.Activity
import android.os.Bundle

class OrderListPresenter(var view: OrderListContracts.View?) : OrderListContracts.Presenter,
    OrderListContracts.InteractorOutput {

    var interactor: OrderListContracts.Interactor? = OrderListInteractor(this)
    var router: OrderListContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = OrderListRouter(activity)

        bundle?.let {

        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    override fun onBtnAddClicked() {
        router?.goToOrderFormCorp()
    }

}
