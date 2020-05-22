package com.app.gastranetwork.module.order_form_service

import android.app.Activity
import android.os.Bundle

class OrderFormServicePresenter(var view: OrderFormServiceContracts.View?) :
    OrderFormServiceContracts.Presenter, OrderFormServiceContracts.InteractorOutput {

    var interactor: OrderFormServiceContracts.Interactor? = OrderFormServiceInteractor(this)
    var router: OrderFormServiceContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = OrderFormServiceRouter(activity)

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

    override fun onSaveFormServiceClick() {
        interactor?.saveOrder()
    }

    override fun onSuccessSaveOrder() {
        view?.successSaveOrder()
    }

}
