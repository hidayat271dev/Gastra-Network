package com.app.gastranetwork.module.splashscreen

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import android.util.Pair
import com.app.gastranetwork.module.dashboard.DashboardActivity
import com.app.gastranetwork.module.login.LoginActivity


class SplashScreenRouter(var activity: Activity?) : SplashScreenContracts.Router {

    override fun unregister() {
        activity = null
    }

    override fun goToLoginPage(ivLogo: View) {
        val intent = Intent(activity, LoginActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, Pair<View, String>(ivLogo, "image_logo"))
        activity?.startActivity(intent, options.toBundle())
        activity?.finish()
    }

    override fun goToDashboardPage(vLogo: View) {
        val intent = Intent(activity, DashboardActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(activity, Pair<View, String>(vLogo, "image_logo"))
        activity?.startActivity(intent, options.toBundle())
        activity?.finish()
    }
}
