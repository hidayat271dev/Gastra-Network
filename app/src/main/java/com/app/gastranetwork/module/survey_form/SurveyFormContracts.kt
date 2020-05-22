package com.app.gastranetwork.module.survey_form

import com.app.gastranetwork.base.BaseContracts

object SurveyFormContracts {

    interface View : BaseContracts.View {
        fun successSaveSurvey()

    }

    interface Presenter : BaseContracts.Presenter {
        fun onSaveFormSurveyClick()

    }

    interface Interactor : BaseContracts.Interactor {
        fun saveSurvey()

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {
        fun onSuccessSaveSurvey()

    }

    interface Router : BaseContracts.Router {

    }

}
