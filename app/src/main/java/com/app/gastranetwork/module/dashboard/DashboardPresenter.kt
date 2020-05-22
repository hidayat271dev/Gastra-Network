package com.app.gastranetwork.module.dashboard

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.FragmentManager

class DashboardPresenter(var view: DashboardContracts.View?) : DashboardContracts.Presenter,
    DashboardContracts.InteractorOutput {

    var interactor: DashboardContracts.Interactor? = DashboardInteractor(this)
    var router: DashboardContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = DashboardRouter(activity)

        bundle?.let {

        }
    }

    override fun onDestroy() {
        view = null
        interactor?.unregister()
        interactor = null
        router?.unregister()
        router = null
    }

    override fun onBtnLogoutClicked() {
        router?.goToLogin()
    }

    override fun onBtnOrderListClick(fragmentManager: FragmentManager) {
        router?.goToOrderList(fragmentManager)
    }

    override fun onBtnSurveyListClick(fragmentManager: FragmentManager) {
        router?.goToSurveyList(fragmentManager)
    }

    override fun onBtnCustomerAktifClick(fragmentManager: FragmentManager) {
        router?.goToCustomerAktif(fragmentManager)
    }

}
