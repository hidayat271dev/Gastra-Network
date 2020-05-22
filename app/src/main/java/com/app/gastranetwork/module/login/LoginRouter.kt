package com.app.gastranetwork.module.login

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.View
import com.app.gastranetwork.module.dashboard.DashboardActivity

class LoginRouter(var activity: Activity?) : LoginContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToDashboardPage(ivLogo: View) {
        val intent = Intent(activity, DashboardActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, Pair<View, String>(ivLogo, "image_logo"))
        activity?.startActivity(intent, options.toBundle())
        activity?.finish()
    }

}
