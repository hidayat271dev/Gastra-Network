package com.app.gastranetwork.module.survey_form

import android.app.Activity

class SurveyFormRouter(var activity: Activity?) : SurveyFormContracts.Router {

    override fun unregister() {
        activity = null
    }

}
