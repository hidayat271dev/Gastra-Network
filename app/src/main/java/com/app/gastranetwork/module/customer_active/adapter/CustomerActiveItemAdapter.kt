package com.app.gastranetwork.module.customer_active.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gastranetwork.R

class CustomerActiveItemAdapter() : RecyclerView.Adapter<CustomerActiveItemAdapter.DataHolder>() {

    val datas = mutableListOf<Any>()
    lateinit var listener: CustomerActiveItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_customer_active_marketing_sale, parent, false))
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.bindData(position, datas[position])
    }

    fun setData(data: List<Any>) {
        datas.clear()
        datas.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: Any) {
        datas.add(data)
        notifyDataSetChanged()
    }

    fun resetData() {
        datas.clear()
        notifyDataSetChanged()
    }

    fun removeData(idx: Int) {
        datas.removeAt(idx)
        notifyDataSetChanged()
    }

    inner class DataHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(position: Int, data: Any) {
            itemView.setOnClickListener { listener.onClickItemCustomerActive(position, data) }
        }

    }

    interface CustomerActiveItemListener {
        fun onClickItemCustomerActive(position: Int, data: Any)
    }
}