package com.app.gastranetwork.module.survey_list

import android.app.Activity
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.module.survey_form.SurveyFormFragment

class SurveyListRouter(var activity: Activity?) : SurveyListContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToSurveyFormPage() {
        (activity as BaseActivity).changeFragment((activity as BaseActivity).supportFragmentManager, "SurveyFormFragment", SurveyFormFragment())
    }
}
