package com.app.gastranetwork.module.order_form_contact

class OrderFormContractInteractor(var output: OrderFormContractContracts.InteractorOutput?) :
    OrderFormContractContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
