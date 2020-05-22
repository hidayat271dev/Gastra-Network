package com.app.gastranetwork.module.order_form_contact

import android.app.Activity
import android.os.Bundle

class OrderFormContractPresenter(var view: OrderFormContractContracts.View?) :
    OrderFormContractContracts.Presenter, OrderFormContractContracts.InteractorOutput {

    var interactor: OrderFormContractContracts.Interactor? = OrderFormContractInteractor(this)
    var router: OrderFormContractContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = OrderFormContractRouter(activity)

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

    override fun onBtnYesClicked() {
        router?.goToOrderFormService()
    }
}
