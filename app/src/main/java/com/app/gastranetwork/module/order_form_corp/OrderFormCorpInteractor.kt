package com.app.gastranetwork.module.order_form_corp

class OrderFormCorpInteractor(var output: OrderFormCorpContracts.InteractorOutput?) :
    OrderFormCorpContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
