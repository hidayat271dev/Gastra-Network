package com.app.gastranetwork.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.app.gastranetwork.R
import com.app.gastranetwork.data.firebase.AppFirebase
import com.app.gastranetwork.data.firebase.repository.app_info.AppInfo
import com.app.gastranetwork.data.firebase.repository.app_info.AppInfoListener
import com.app.gastranetwork.data.firebase.repository.app_info.AppInfoRepository
import com.app.gastranetwork.util.AppConstants
import com.app.gastranetwork.util.dialog.*
import com.app.gastranetwork.util.extension.setImageWithColor
import com.app.gastranetwork.util.extension.setShow
import com.google.gson.Gson
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.template_appbar.*
import kotlinx.android.synthetic.main.template_appbar.template_appbar_iv_left
import kotlinx.android.synthetic.main.template_appbar.template_appbar_tv_title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


abstract class BaseActivity: AppCompatActivity(), BaseContracts.View
    , BottomMessageDialogListener, CenterMessageDialogListener, FullMessageDialogListener
    , AppInfoListener {

    var bottomMessageDialog: BottomMessageDialog? = null
    var centerMessageDialog: CenterMessageDialog? = null
    var fullMessageDialog: FullMessageDialog? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun getActivityContext(): Context? {
        return this
    }

    override fun onBackPressed() {
        var listFragment = supportFragmentManager.fragments
        var handle = false
        for(fragment in listFragment) {

            if (fragment is BaseFragment) {
                handle = fragment.onBackPressed()

                if(handle) {
                    break
                }
            }
        }

        if(!handle) {
            super.onBackPressed()
        }
    }

    fun initPreference() {
        Hawk.init(applicationContext).build()
    }

    fun clearStack() {
        setDefaultAppBar()
        val backStackEntry = supportFragmentManager.backStackEntryCount
        if (backStackEntry > 0) {
            for (i in 0 until backStackEntry - 1) {
                supportFragmentManager.popBackStackImmediate()
            }
        }

        if (supportFragmentManager.fragments != null && supportFragmentManager.fragments.size > 0) {
            for (i in supportFragmentManager.fragments.indices) {
                val mFragment =
                    supportFragmentManager.fragments[i]
                if (mFragment != null) {
                    supportFragmentManager.beginTransaction().remove(mFragment).commit()
                }
            }
        }
    }

    fun changeFragment(
        fragmentManager: FragmentManager,
        tag: String,
        fragment: Fragment,
        container: Int = R.id.container,
        isBackToStack: Boolean = true,
        backStack: String? = null
    ) {
        var transaction = fragmentManager.beginTransaction()
        transaction
            .replace(container, fragment, tag)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

        if(isBackToStack) {
            transaction.addToBackStack(backStack)
        }

        transaction.commit()
    }

    /* ========================== TOOLBAR ========================== */
    fun setDefaultAppBar(isDefault: Boolean = true) {
        appbar.setShow(true)
    }

    fun changeLayoutToolbar(
        isBackAll: Boolean = false,
        isToolbarLeft: Boolean = true, imgToolbarLeft: Int = R.drawable.ic_back, colorToolbarLeftTint: Int? = R.color.colorWhite,
        isToolbarTitle: Boolean = true, textToolbarTitle: String = "Title Here", colorToolbarTitleTint: Int? = R.color.colorWhite,
        isToolbarRight: Boolean = true, imgToolbarRight: Int = R.drawable.ic_cancel, colorToolbarRightTint: Int? = R.color.colorWhite
    ) {
        appbar.setShow(false)

        if(isBackAll) {
            template_appbar_iv_left.setOnClickListener {
                clearStack()
                appbar.setShow(true)
            }
        } else {
            template_appbar_iv_left.setOnClickListener {
                onBackPressed()
            }
        }

        if (isToolbarLeft) {
            template_appbar_iv_left.setShow(true)
            if(colorToolbarLeftTint != null) template_appbar_iv_left.setImageWithColor(imgToolbarLeft, colorToolbarLeftTint)
        } else {
            template_appbar_iv_left.setShow(false)
        }

        if (isToolbarTitle) {
            template_appbar_tv_title.setShow(true)
            template_appbar_tv_title.text = textToolbarTitle
            if(colorToolbarTitleTint != null) applicationContext.let { template_appbar_tv_title.setTextColor(
                ContextCompat.getColor(it, colorToolbarTitleTint)) }

        } else {
            template_appbar_tv_title.setShow(false)
        }

        if (isToolbarRight) {
            template_appbar_iv_rigth.setShow(true)
            if(colorToolbarRightTint != null) template_appbar_iv_rigth.setImageWithColor(imgToolbarLeft, colorToolbarRightTint)
        } else {
            template_appbar_iv_rigth.setShow(false)
        }
    }

    /* ========================== TOOLBAR ========================== */

    /* ========================== CHECK APP INFO ========================== */

    override fun getDataAppInfo(temp: Any) {
        var data = Gson().fromJson(temp.toString(), AppInfo::class.java)
        if(data.is_maintenance != null) {
            if (data.is_maintenance!!) {
                showFullDialogMessage(typeMessage = FullMessageDialogType.MESSAGE_ONLY_NO_CANCEL, title = "Oh No!!!", content = "Application under maintenance", img = R.drawable.img_maintenance)
                return
            } else {
                if(fullMessageDialog != null) {
                    fullMessageDialog?.dismissAllowingStateLoss()
                }
            }
        }

        if(data.latest_version != null) {
            if(!data.latest_version.equals(AppConstants.APP_VERSION)) {
                showFullDialogMessage(typeMessage = FullMessageDialogType.MESSAGE_ONLY_NO_CANCEL, title = "Oh No!!!", content = "Application out of date", img = R.drawable.img_update)
                return
            } else {
                if(fullMessageDialog != null) {
                    fullMessageDialog?.dismissAllowingStateLoss()
                }
            }
        }
    }

    fun checkAppInfo() {
        AppInfoRepository.listener = this
        AppFirebase.instance.firebaseDatabase.child(AppInfoRepository.path_name).addValueEventListener(AppInfoRepository)
    }
    /* ========================== CHECK APP INFO ========================== */

    /* ========================== CHECK CONNECTION ========================== */

    fun checkNetwork() {
        val cm: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val nInfo = cm.activeNetworkInfo
        val connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected
        lifecycleScope.launch {
            showMessageConnection(connected)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val builder: NetworkRequest.Builder = NetworkRequest.Builder()
            cm.registerNetworkCallback(
                builder.build(),
                object : ConnectivityManager.NetworkCallback() {

                    override fun onAvailable(network: Network) {
                        lifecycleScope.launch {
                            val isWifi:Boolean = cm.getNetworkCapabilities(network).hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                            showMessageConnection(true, isWifi)
                        }
                    }

                    override fun onLost(network: Network) {
                        lifecycleScope.launch {
                            showMessageConnection(false)
                        }
                    }
                }
            )
        }
    }

    private suspend fun showMessageConnection(isConnected:Boolean, isWifi:Boolean= false){
        withContext(Dispatchers.Main){
            if(isConnected) {
                if(fullMessageDialog != null) {
                    fullMessageDialog?.dismissAllowingStateLoss()
                }
            }else {
                showFullDialogMessage(typeMessage = FullMessageDialogType.MESSAGE_ONLY_NO_CANCEL, title = "Oh No!!!", content = "You are offline", img = R.drawable.img_noconnection)
            }
        }
    }

    /* ========================== CHECK CONNECTION ========================== */

    /* ========================== BOTTOM MESSAGE DIALOG ========================== */
    fun showBottomDialogMessage(typeMessage: BottomMessageDialogType = BottomMessageDialogType.MESSAGE_ONLY, title: String = "", content: String = "", img: Int = 0) : BottomMessageDialog? {
        bottomMessageDialog = BottomMessageDialog.newInstance(messageDialogType = typeMessage, title = title, content = content, img = img)
        bottomMessageDialog?.listener = this
        bottomMessageDialog?.let {
            supportFragmentManager.beginTransaction().add(it, it.TAG).commit()
        }
        return bottomMessageDialog
    }

    override fun onBottomMessageNoClicked() {
        bottomMessageDialog?.dismissWithBehavior()
    }

    override fun onBottomMessageYesClicked() {
        bottomMessageDialog?.dismissWithBehavior()
    }
    /* ========================== BOTTOM MESSAGE DIALOG ========================== */

    /* ========================== CENTER MESSAGE DIALOG ========================== */

    fun showCenterDialogMessage(typeMessage: CenterMessageDialogType = CenterMessageDialogType.MESSAGE_ONLY, title: String = "", content: String = "", img: Int = 0) {
        centerMessageDialog = CenterMessageDialog.newInstance(messageDialogType = typeMessage, title = title, content = content, img = img)
        centerMessageDialog?.listener = this
        centerMessageDialog?.let {
            supportFragmentManager.beginTransaction().add(it, it.TAG).commit()
        }
    }

    override fun onCenterMessageNoClicked() {
        centerMessageDialog?.dismissAllowingStateLoss()
    }

    override fun onCenterMessageYesClicked() {
        centerMessageDialog?.dismissAllowingStateLoss()
    }

    /* ========================== CENTER MESSAGE DIALOG ========================== */

    /* ========================== FULL MESSAGE DIALOG ========================== */

    fun showFullDialogMessage(typeMessage: FullMessageDialogType = FullMessageDialogType.MESSAGE_ONLY, title: String = "", content: String = "", img: Int = 0) {
        fullMessageDialog = FullMessageDialog.newInstance(messageDialogType = typeMessage, title = title, content = content, img = img)
        fullMessageDialog?.listener = this
        fullMessageDialog?.let {
            supportFragmentManager.beginTransaction().add(it, it.TAG).commit()
        }
    }

    override fun onFullMessageNoClicked() {
        fullMessageDialog?.dismissAllowingStateLoss()
    }

    override fun onFullMessageYesClicked() {
        fullMessageDialog?.dismissAllowingStateLoss()
    }

    /* ========================== FULL MESSAGE DIALOG ========================== */

    /* ========================== PROGRESS DIALOG ========================== */

    fun isShowProgressDialog(isShow: Boolean, title: String = "Processing", content: String = "Please wait...") {
        if(isShow) {
            if (progressDialog != null) {
                progressDialog?.dismissAllowingStateLoss()
            }
            progressDialog = ProgressDialog.newInstance(title = title, content = content)
            progressDialog?.let { supportFragmentManager.beginTransaction().add(it, it.TAG).commit() }
        }
        else {
            progressDialog?.dismissAllowingStateLoss()
        }
    }

    /* ========================== PROGRESS DIALOG ========================== */
}
