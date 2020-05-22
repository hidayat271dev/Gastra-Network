package com.app.gastranetwork.module.order_form_corp

import android.app.Activity
import android.os.Bundle

class OrderFormCorpPresenter(var view: OrderFormCorpContracts.View?) :
    OrderFormCorpContracts.Presenter, OrderFormCorpContracts.InteractorOutput {

    var interactor: OrderFormCorpContracts.Interactor? = OrderFormCorpInteractor(this)
    var router: OrderFormCorpContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = OrderFormCorpRouter(activity)

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

    override fun onBtnSaveClicked() {
        router?.goToOrderFormContact()
    }

}
