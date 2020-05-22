package com.app.gastranetwork.module.order_form_service

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseFragment
import com.app.gastranetwork.util.AppConstants
import com.app.gastranetwork.util.dialog.BottomMessageDialog
import com.app.gastranetwork.util.dialog.BottomMessageDialogListener
import com.app.gastranetwork.util.dialog.BottomMessageDialogType
import com.app.gastranetwork.util.dialog.FullMessageDialogType
import kotlinx.android.synthetic.main.fragment_order_form_service.*

class OrderFormServiceFragment : BaseFragment(), OrderFormServiceContracts.View
    , BottomMessageDialogListener {

    var presenter: OrderFormServiceContracts.Presenter? = OrderFormServicePresenter(this)
    var confirmCancelDialog: BottomMessageDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_form_service, container, false)
    }

    override fun onBackPressed(): Boolean {
        getBaseActivity()?.setDefaultAppBar()
        Log.d("BACK", "Press")
        return super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        presenter?.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
        presenter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter?.onCreate()
        setupView()
    }

    override fun onBottomMessageYesClicked() {
        getBaseActivity()?.isShowProgressDialog(true)
        confirmCancelDialog?.dismissWithBehavior()
        Handler().postDelayed({
            presenter?.onSaveFormServiceClick()
        }, AppConstants.DELAY)
    }

    override fun onBottomMessageNoClicked() {
        confirmCancelDialog?.dismissWithBehavior()
    }

    fun setupView() {
        getBaseActivity()?.changeLayoutToolbar(
            isBackAll = true,
            isToolbarLeft = true,
            textToolbarTitle = "Form Order",
            isToolbarRight = false
        )

        fragment_order_form_service_btn_cancel.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        fragment_order_form_service_btn_yes.setOnClickListener {
            confirmCancelDialog = getBaseActivity()?.showBottomDialogMessage(
                typeMessage = BottomMessageDialogType.MESSAGE_WITH_CONFIRM,
                title = "Process Order",
                content = "Are you sure to save this order?"
            )
            confirmCancelDialog?.listener = this
        }

        fragment_order_form_service_s_custom.setEditable(false)

        fragment_survey_form_s_custom.setOnCheckedChangeListener {
            if(it) {
                fragment_order_form_service_s_custom.setEditable(true)
            } else {
                fragment_order_form_service_s_custom.setText("")
                fragment_order_form_service_s_custom.setEditable(false)
            }
        }
    }

    override fun successSaveOrder() {
        getBaseActivity()?.isShowProgressDialog(false)
        getBaseActivity()?.clearStack()
        onBackPressed()

        getBaseActivity()?.showFullDialogMessage(
            typeMessage = FullMessageDialogType.MESSAGE_WITH_BUTTON,
            title = "Success Save Data",
            content = "Successful save data order"
        )
    }

}
