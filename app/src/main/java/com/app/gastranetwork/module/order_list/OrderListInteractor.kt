package com.app.gastranetwork.module.order_list

class OrderListInteractor(var output: OrderListContracts.InteractorOutput?) :
    OrderListContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
