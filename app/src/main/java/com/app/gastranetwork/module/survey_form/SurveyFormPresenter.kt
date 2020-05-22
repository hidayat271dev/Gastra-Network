package com.app.gastranetwork.module.survey_form

import android.app.Activity
import android.os.Bundle

class SurveyFormPresenter(var view: SurveyFormContracts.View?) : SurveyFormContracts.Presenter,
    SurveyFormContracts.InteractorOutput {

    var interactor: SurveyFormContracts.Interactor? = SurveyFormInteractor(this)
    var router: SurveyFormContracts.Router? = null

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        val activity = view?.getActivityContext() as? Activity ?: return
        router = SurveyFormRouter(activity)

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

    override fun onSaveFormSurveyClick() {
        interactor?.saveSurvey()
    }

    override fun onSuccessSaveSurvey() {
        view?.successSaveSurvey()
    }

}
