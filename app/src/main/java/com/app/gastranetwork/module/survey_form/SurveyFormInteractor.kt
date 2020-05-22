package com.app.gastranetwork.module.survey_form

class SurveyFormInteractor(var output: SurveyFormContracts.InteractorOutput?) :
    SurveyFormContracts.Interactor {

    override fun unregister() {
        output = null
    }

    override fun saveSurvey() {
        output?.onSuccessSaveSurvey()
    }

}
