package com.app.gastranetwork.module.customer_active

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseFragment
import com.app.gastranetwork.module.customer_active.adapter.CustomerActiveItemAdapter
import kotlinx.android.synthetic.main.fragment_customer_active.*

class CustomerActiveFragment : BaseFragment(), CustomerActiveContracts.View
    , CustomerActiveItemAdapter.CustomerActiveItemListener {

    var presenter: CustomerActiveContracts.Presenter? = CustomerActivePresenter(this)
    var adapterItemCustomer = CustomerActiveItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_active, container, false)
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
            isToolbarLeft = true,
            textToolbarTitle = "Customer Aktif",
            isToolbarRight = false
        )

        adapterItemCustomer.listener = this
        fragment_customer_active_rv_data.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragment_customer_active_rv_data.adapter = adapterItemCustomer

        for (i in 0 until 15) {
            adapterItemCustomer.addData("")
        }
    }

    override fun onClickItemCustomerActive(position: Int, data: Any) {
        Log.d("onClickItem", "Position ${position} ${data.toString()}")
    }

}
