package com.app.gastranetwork.module.login

import android.os.Bundle
import android.os.Handler
import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.util.AppConstants
import com.app.gastranetwork.util.dialog.BottomMessageDialogType
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_splashscreen.*

class LoginActivity : BaseActivity(), LoginContracts.View {

    var presenter: LoginContracts.Presenter? = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
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

        activity_login_btn_login.setOnClickListener {
            if(validateInput()) {
                isShowProgressDialog(true)
                Handler().postDelayed({
                    presenter?.onBtnLoginPress(activity_login_iv_logo, activity_login_et_username.text, activity_login_et_password.text)
                }, AppConstants.DELAY)
            }
        }
    }

    fun validateInput() : Boolean {
        if(activity_login_et_username.text == "") {
            showBottomDialogMessage(typeMessage = BottomMessageDialogType.MESSAGE_ONLY, title = "Error input", content = "Please input username")
            activity_login_et_username.isFocusable = true
            return false
        }
        if(activity_login_et_password.text == "") {
            showBottomDialogMessage(typeMessage = BottomMessageDialogType.MESSAGE_ONLY, title = "Error input", content = "Please insert password")
            activity_login_et_password.isFocusable = true
            return false
        }
        return true
    }

    override fun failedLogin() {
        showBottomDialogMessage(typeMessage = BottomMessageDialogType.MESSAGE_WITH_BUTTON, title = "Login failed", content = "Username not found or password not match")
        activity_login_et_username.isFocusable = true
        isShowProgressDialog(false)
    }
}
