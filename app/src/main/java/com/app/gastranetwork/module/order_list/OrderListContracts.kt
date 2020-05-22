package com.app.gastranetwork.module.order_list

import com.app.gastranetwork.base.BaseContracts

object OrderListContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun onBtnAddClicked()

    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToOrderFormCorp()

    }

}
