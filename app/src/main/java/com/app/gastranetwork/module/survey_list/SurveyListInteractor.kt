package com.app.gastranetwork.module.survey_list

class SurveyListInteractor(var output: SurveyListContracts.InteractorOutput?) :
    SurveyListContracts.Interactor {

    override fun unregister() {
        output = null
    }

}
