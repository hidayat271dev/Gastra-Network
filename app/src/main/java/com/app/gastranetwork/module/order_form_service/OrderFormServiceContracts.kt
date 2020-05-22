package com.app.gastranetwork.module.order_form_service

import com.app.gastranetwork.base.BaseContracts

object OrderFormServiceContracts {

    interface View : BaseContracts.View {
        fun successSaveOrder()

    }

    interface Presenter : BaseContracts.Presenter {
        fun onSaveFormServiceClick()

    }

    interface Interactor : BaseContracts.Interactor {
        fun saveOrder()

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun onSuccessSaveOrder()

    }

    interface Router : BaseContracts.Router {

    }

}
