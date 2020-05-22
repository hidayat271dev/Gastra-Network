package com.app.gastranetwork.module.dashboard

class DashboardInteractor(var output: DashboardContracts.InteractorOutput?) :
    DashboardContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
