package com.app.gastranetwork.module.order_form_corp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseFragment
import com.app.gastranetwork.util.dialog.BottomMessageDialog
import com.app.gastranetwork.util.dialog.BottomMessageDialogListener
import com.app.gastranetwork.util.dialog.BottomMessageDialogType
import kotlinx.android.synthetic.main.fragment_order_form_corp.*

class OrderFormCorpFragment : BaseFragment(), OrderFormCorpContracts.View
    , BottomMessageDialogListener {

    var presenter: OrderFormCorpContracts.Presenter? = OrderFormCorpPresenter(this)
    var confirmCancelDialog: BottomMessageDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_form_corp, container, false)
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
        confirmCancelDialog?.dismissWithBehavior()
        fragmentManager?.popBackStack()
        onBackPressed()
    }

    override fun onBottomMessageNoClicked() {
        confirmCancelDialog?.dismissWithBehavior()
    }

    fun setupView() {
        getBaseActivity()?.changeLayoutToolbar(
            isToolbarLeft = true,
            textToolbarTitle = "Form Order",
            isToolbarRight = false
        )

        fragment_order_form_corp_cancel.setOnClickListener {
            confirmCancelDialog = getBaseActivity()?.showBottomDialogMessage(
                typeMessage = BottomMessageDialogType.MESSAGE_WITH_CONFIRM,
                title = "Cancel Order",
                content = "Are you sure to cancel this order?"
            )
            confirmCancelDialog?.listener = this
        }

        fragment_order_form_corp_yes.setOnClickListener {
            presenter?.onBtnSaveClicked()
        }
    }
}
