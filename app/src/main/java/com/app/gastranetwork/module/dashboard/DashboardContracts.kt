package com.app.gastranetwork.module.dashboard

import androidx.fragment.app.FragmentManager
import com.app.gastranetwork.base.BaseContracts

object DashboardContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun onBtnLogoutClicked()
        fun onBtnOrderListClick(fragmentManager: FragmentManager)
        fun onBtnSurveyListClick(fragmentManager: FragmentManager)
        fun onBtnCustomerAktifClick(supportFragmentManager: FragmentManager)
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToLogin()
        fun goToOrderList(fragmentManager: FragmentManager)
        fun goToSurveyList(fragmentManager: FragmentManager)
        fun goToCustomerAktif(fragmentManager: FragmentManager)

    }

}
