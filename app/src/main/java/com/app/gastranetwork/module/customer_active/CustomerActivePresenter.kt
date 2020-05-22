package com.app.gastranetwork.module.customer_active

import android.app.Activity
import android.os.Bundle

class CustomerActivePresenter(var view: CustomerActiveContracts.View?) :
    CustomerActiveContracts.Presenter, CustomerActiveContracts.InteractorOutput {

    var interactor: CustomerActiveContracts.Interactor? = CustomerActiveInteractor(this)
    var router: CustomerActiveContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = CustomerActiveRouter(activity)

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

}
