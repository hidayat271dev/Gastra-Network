package com.app.gastranetwork.module.splashscreen

import android.os.Bundle
import android.os.Handler
import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.util.AppConstants
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashScreenActivity : BaseActivity(), SplashScreenContracts.View {

    var presenter: SplashScreenContracts.Presenter? = SplashScreenPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        presenter?.onCreate(intent.extras)

        setupView()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        presenter?.onDestroy()
        presenter = null
        super.onDestroy()
    }

    fun setupView() {
        initPreference()
        checkNetwork()
        checkAppInfo()

        activity_splashscreen_tv_version.text = getString(R.string.lbl_version).plus(" ${AppConstants.APP_VERSION}")

        Handler().postDelayed({
            presenter?.checkIsLoggin(activity_splashscreen_iv_logo)
        }, AppConstants.DELAY)
    }

}
