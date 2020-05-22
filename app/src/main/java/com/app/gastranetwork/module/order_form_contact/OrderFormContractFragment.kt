package com.app.gastranetwork.module.order_form_contact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_order_form_contact.*
import kotlinx.android.synthetic.main.template_appbar.*

class OrderFormContractFragment : BaseFragment(), OrderFormContractContracts.View {

    var presenter: OrderFormContractContracts.Presenter? = OrderFormContractPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_form_contact, container, false)
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

    fun setupView() {
        getBaseActivity()?.changeLayoutToolbar(
            isBackAll = true,
            isToolbarLeft = true,
            textToolbarTitle = "Form Order",
            isToolbarRight = false
        )

        fragment_order_form_contract_s_reseller.setOnCheckedChangeListener {
            fragment_order_form_contract_sp_reseller.selectItemByIndex(0)
        }

        fragment_order_form_contract_btn_cancel.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        fragment_order_form_contract_btn_yes.setOnClickListener {
            presenter?.onBtnYesClicked()
        }
    }

}
