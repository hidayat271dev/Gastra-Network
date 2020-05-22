package com.app.gastranetwork.module.customer_active

class CustomerActiveInteractor(var output: CustomerActiveContracts.InteractorOutput?) :
    CustomerActiveContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
