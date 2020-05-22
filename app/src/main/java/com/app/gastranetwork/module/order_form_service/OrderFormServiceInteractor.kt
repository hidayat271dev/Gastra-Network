package com.app.gastranetwork.module.order_form_service

class OrderFormServiceInteractor(var output: OrderFormServiceContracts.InteractorOutput?) :
    OrderFormServiceContracts.Interactor {

    override fun unregister() {
        output = null
    }

    override fun saveOrder() {
        output?.onSuccessSaveOrder()
    }
}
