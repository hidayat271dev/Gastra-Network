package com.app.gastranetwork.module.order_form_corp

import com.app.gastranetwork.base.BaseContracts

object OrderFormCorpContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun onBtnSaveClicked()

    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToOrderFormContact()

    }

}
