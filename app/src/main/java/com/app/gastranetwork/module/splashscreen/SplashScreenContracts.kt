package com.app.gastranetwork.module.splashscreen

import android.view.View
import android.widget.ImageView
import com.app.gastranetwork.base.BaseContracts

object SplashScreenContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun checkIsLoggin(vLogo: android.view.View)
    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToLoginPage(ivLogo: android.view.View)
        fun goToDashboardPage(vLogo: android.view.View)
    }

}
