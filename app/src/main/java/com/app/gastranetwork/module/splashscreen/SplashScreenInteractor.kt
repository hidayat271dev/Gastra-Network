package com.app.gastranetwork.module.splashscreen

class SplashScreenInteractor(var output: SplashScreenContracts.InteractorOutput?) :
    SplashScreenContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
