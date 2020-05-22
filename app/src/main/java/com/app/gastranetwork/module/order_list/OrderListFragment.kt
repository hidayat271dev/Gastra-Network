package com.app.gastranetwork.module.order_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseFragment
import com.app.gastranetwork.module.order_list.adapter.OrderListItemAdapter
import kotlinx.android.synthetic.main.fragment_order_list.*

class OrderListFragment : BaseFragment(), OrderListContracts.View
    , OrderListItemAdapter.OrderListItemListener {

    var presenter: OrderListContracts.Presenter? = OrderListPresenter(this)
    var adapterItemOrder = OrderListItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_list, container, false)
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
        getBaseActivity()?.changeLayoutToolbar(isToolbarLeft = true, textToolbarTitle = "List Order", isToolbarRight = false)

        adapterItemOrder.listener = this
        fragment_order_list_rv_data.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragment_order_list_rv_data.adapter = adapterItemOrder

        for (i in 0 until 15) {
            adapterItemOrder.addData("")
        }

        fragment_order_list_btn_add.setOnClickListener {
            presenter?.onBtnAddClicked()
        }
    }

    override fun onClickItemOrder(position: Int, data: Any) {
        Log.d("onClickItem", "Position ${position} ${data.toString()}")
    }

}
