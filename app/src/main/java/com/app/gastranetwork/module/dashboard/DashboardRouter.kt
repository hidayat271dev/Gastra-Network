package com.app.gastranetwork.module.dashboard

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.data.preference.AppPreference
import com.app.gastranetwork.module.customer_active.CustomerActiveFragment
import com.app.gastranetwork.module.login.LoginActivity
import com.app.gastranetwork.module.order_form_corp.OrderFormCorpFragment
import com.app.gastranetwork.module.order_list.OrderListFragment
import com.app.gastranetwork.module.survey_form.SurveyFormFragment
import com.app.gastranetwork.module.survey_list.SurveyListFragment

class DashboardRouter(var activity: Activity?) : DashboardContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToLogin() {
        AppPreference.instance.setToken(null)
        AppPreference.instance.setIsLogin(false)
        AppPreference.instance.setUserAccess(null)

        var intent = Intent(activity, LoginActivity::class.java)
        activity?.startActivity(intent)
        activity?.finish()
    }

    override fun goToOrderList(fragmentManager: FragmentManager) {
//        var intent = Intent(activity, OrderFormActivity::class.java)
//        activity?.startActivity(intent)
        (activity as BaseActivity).changeFragment(fragmentManager, "OrderListFragment", OrderListFragment())
    }

    override fun goToSurveyList(fragmentManager: FragmentManager) {
//        var intent = Intent(activity, SurveyFormActivity::class.java)
//        activity?.startActivity(intent)
        (activity as BaseActivity).changeFragment(fragmentManager, "SurveyListFragment", SurveyListFragment())
    }

    override fun goToCustomerAktif(fragmentManager: FragmentManager) {
        (activity as BaseActivity).changeFragment(fragmentManager, "CustomerActiveFragment", CustomerActiveFragment())
    }
}
