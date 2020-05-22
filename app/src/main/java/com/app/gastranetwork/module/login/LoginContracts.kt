package com.app.gastranetwork.module.login

import android.view.View
import com.app.gastranetwork.base.BaseContracts
import com.app.gastranetwork.data.preference.repository.UserAccess

object LoginContracts {

    interface View : BaseContracts.View {
        fun failedLogin()
    }

    interface Presenter : BaseContracts.Presenter {
        fun onBtnLoginPress(ivLogo: android.view.View, username: String, password: String)
    }

    interface Interactor : BaseContracts.Interactor {
        fun setUserAccess(userAccess: UserAccess)
    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToDashboardPage(ivLogo: android.view.View)
    }

}
