package com.app.gastranetwork.module.dashboard

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseActivity
import com.app.gastranetwork.data.preference.AppPreference
import com.app.gastranetwork.data.preference.repository.UserAccess
import com.app.gastranetwork.module.dashboard.adapter.DashboardItemSurveyMarketingSaleAdapter
import com.app.gastranetwork.util.AppConstants
import com.app.gastranetwork.util.dialog.BottomMessageDialogType
import com.app.gastranetwork.util.dialog.CenterMessageDialogType
import com.app.gastranetwork.util.extension.setShow
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.menu_customer.*
import kotlinx.android.synthetic.main.menu_owner.*
import kotlinx.android.synthetic.main.menu_sale.*
import kotlinx.android.synthetic.main.menu_teknisi.*

class DashboardActivity : BaseActivity(), DashboardContracts.View
    , DashboardItemSurveyMarketingSaleAdapter.DashboardItemSurveyMarketingSaleListener {

    var presenter: DashboardContracts.Presenter? = DashboardPresenter(this)

    var adapterItemSurveyMarketing = DashboardItemSurveyMarketingSaleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
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

    override fun onBottomMessageYesClicked() {
        super.onBottomMessageYesClicked()
        presenter?.onBtnLogoutClicked()
    }

    fun setupView() {
        initPreference()
        checkAppInfo()
        checkNetwork()

        activity_dashboard_iv_logout.setOnClickListener {
            showBottomDialogMessage(typeMessage = BottomMessageDialogType.MESSAGE_WITH_CONFIRM, title = "Logout App", content = "Are you sure logout?")
        }

        isShowProgressDialog(true)
        Handler().postDelayed({
            setMenuUser()
            isShowProgressDialog(false)
        }, AppConstants.DELAY)
    }

    fun setMenuUser() {
        activity_dashboard_container_menu.setShow(false)
        val inflater = LayoutInflater.from(applicationContext)
        when(AppPreference.instance.getUserAccess()) {
            UserAccess.MARKETING_SALE -> {
                setupMenuMarketing(inflater)
                setupInformationMarketing()
            }
            UserAccess.CUSTOMER -> setupMenuCustomer(inflater)
            UserAccess.TEKNISI -> setupMenuTeknisi(inflater)
            else -> setupMenuOwner(inflater)
        }
    }

    private fun setupMenuOwner(inflater: LayoutInflater?) {
        activity_dashboard_content_menu.addView(inflater?.inflate(R.layout.menu_owner, null, false))
        activity_dashboard_container_menu.setShow(true)
        menu_owner_laporan_customer.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_owner_laporan_omset.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_owner_laporan_teknis.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_owner_laporan_ts.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
    }

    private fun setupMenuTeknisi(inflater: LayoutInflater?) {
        activity_dashboard_content_menu.addView(inflater?.inflate(R.layout.menu_teknisi, null, false))
        activity_dashboard_container_menu.setShow(true)
        menu_teknisi_bantuan_teknisi.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_teknisi_hasil_survey.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_teknisi_pengajuan_survey.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_teknisi_stop_langganan.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
    }

    private fun setupMenuCustomer(inflater: LayoutInflater?) {
        activity_dashboard_content_menu.addView(inflater?.inflate(R.layout.menu_customer, null, false))
        activity_dashboard_container_menu.setShow(true)
        menu_customer_bantuan_teknis.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_customer_billing.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_customer_form_pemberhentian.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
        menu_customer_order_form.setOnClickListener {
            presenter?.onBtnOrderListClick(supportFragmentManager)
        }
        menu_customer_perubahan_data.setOnClickListener { showCenterDialogMessage(typeMessage = CenterMessageDialogType.MESSAGE_WITH_BUTTON, title = "Sorry", content = "This feature is unavailable") }
    }

    private fun setupMenuMarketing(inflater: LayoutInflater?) {
        activity_dashboard_content_menu.addView(inflater?.inflate(R.layout.menu_sale, null, false))
        activity_dashboard_container_menu.setShow(true)
        menu_sale_customer_aktif.setOnClickListener {
            presenter?.onBtnCustomerAktifClick(supportFragmentManager)
        }
        menu_sale_order_form.setOnClickListener { presenter?.onBtnOrderListClick(supportFragmentManager) }
        menu_sale_survey_form.setOnClickListener {
            presenter?.onBtnSurveyListClick(supportFragmentManager)
        }
    }

    private fun setupInformationMarketing() {
        adapterItemSurveyMarketing.listener = this
        activity_dashboard_rv_data.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_dashboard_rv_data.adapter = adapterItemSurveyMarketing

        for (i in 0 until 15) {
            adapterItemSurveyMarketing.addData("")
        }
    }

    override fun onClickItemSurveyMarketing(position: Int, data: Any) {
        Log.d("onClickItem", "Position ${position} ${data.toString()}")
    }

}
