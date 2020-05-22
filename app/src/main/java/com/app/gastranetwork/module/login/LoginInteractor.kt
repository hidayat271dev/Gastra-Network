package com.app.gastranetwork.module.login

import com.app.gastranetwork.data.preference.AppPreference
import com.app.gastranetwork.data.preference.repository.UserAccess

class LoginInteractor(var output: LoginContracts.InteractorOutput?) : LoginContracts.Interactor {

    override fun unregister() {
        output = null
    }

    override fun setUserAccess(userAccess: UserAccess) {
        AppPreference.instance.setIsLogin(true)
        AppPreference.instance.setUserAccess(userAccess)
    }
}
