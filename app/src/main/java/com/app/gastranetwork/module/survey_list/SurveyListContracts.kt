package com.app.gastranetwork.module.survey_list

import com.app.gastranetwork.base.BaseContracts

object SurveyListContracts {

    interface View : BaseContracts.View {

    }

    interface Presenter : BaseContracts.Presenter {
        fun onBtnAddClicked()

    }

    interface Interactor : BaseContracts.Interactor {

    }

    interface InteractorOutput : BaseContracts.InteractorOutput {

    }

    interface Router : BaseContracts.Router {
        fun goToSurveyFormPage()

    }

}
