package com.app.gastranetwork.module.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.app.gastranetwork.data.preference.repository.UserAccess

class LoginPresenter(var view: LoginContracts.View?) : LoginContracts.Presenter,
    LoginContracts.InteractorOutput {

    var interactor: LoginContracts.Interactor? = LoginInteractor(this)
    var router: LoginContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = LoginRouter(activity)

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

    override fun onBtnLoginPress(ivLogo: View, username: String, password: String) {
        if(username.equals(UserAccess.MARKETING_SALE.access_name) && password.equals("12345")) {
            interactor?.setUserAccess(UserAccess.MARKETING_SALE)
            router?.goToDashboardPage(ivLogo)
        } else if(username.equals(UserAccess.TEKNISI.access_name) && password.equals("12345")) {
            interactor?.setUserAccess(UserAccess.TEKNISI)
            router?.goToDashboardPage(ivLogo)
        } else if(username.equals(UserAccess.CUSTOMER.access_name) && password.equals("12345")) {
            interactor?.setUserAccess(UserAccess.CUSTOMER)
            router?.goToDashboardPage(ivLogo)
        } else if(username.equals(UserAccess.OWNER.access_name) && password.equals("12345")) {
            interactor?.setUserAccess(UserAccess.OWNER)
            router?.goToDashboardPage(ivLogo)
        } else {
            view?.failedLogin()
        }
    }

}
