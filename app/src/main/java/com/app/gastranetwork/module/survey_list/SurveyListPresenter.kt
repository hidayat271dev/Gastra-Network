package com.app.gastranetwork.module.survey_list

import android.app.Activity
import android.os.Bundle

class SurveyListPresenter(var view: SurveyListContracts.View?) : SurveyListContracts.Presenter,
    SurveyListContracts.InteractorOutput {

    var interactor: SurveyListContracts.Interactor? = SurveyListInteractor(this)
    var router: SurveyListContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = SurveyListRouter(activity)

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

    override fun onBtnAddClicked() {
        router?.goToSurveyFormPage()
    }

}
