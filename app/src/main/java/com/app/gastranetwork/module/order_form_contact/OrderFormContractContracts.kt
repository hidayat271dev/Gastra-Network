package com.app.gastranetwork.module.order_form_contact

import com.app.gastranetwork.base.BaseContracts

object OrderFormContractContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun onBtnYesClicked()

    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToOrderFormService()

    }

}
