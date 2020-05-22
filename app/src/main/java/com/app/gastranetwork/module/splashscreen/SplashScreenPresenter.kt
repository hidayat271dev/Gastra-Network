package com.app.gastranetwork.module.splashscreen

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.app.gastranetwork.data.preference.AppPreference

class SplashScreenPresenter(var view: SplashScreenContracts.View?) :
    SplashScreenContracts.Presenter, SplashScreenContracts.InteractorOutput {

    var interactor: SplashScreenContracts.Interactor? = SplashScreenInteractor(this)
    var router: SplashScreenContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = SplashScreenRouter(activity)

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

    override fun checkIsLoggin(vLogo: View) {
        var isLoggin = AppPreference.instance.getIsLogin()
        if (isLoggin != null) {
            if(isLoggin) {
                router?.goToDashboardPage(vLogo)
            } else {
                router?.goToLoginPage(vLogo)
            }
        }
    }

}
