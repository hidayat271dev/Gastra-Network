package com.app.gastranetwork.module.survey_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.gastranetwork.R

class SurveyListItemAdapter() : RecyclerView.Adapter<SurveyListItemAdapter.DataHolder>() {

    val datas = mutableListOf<Any>()
    lateinit var listener: SurveyListItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_survey_marketing_sale, parent, false))
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
            itemView.setOnClickListener { listener.onClickItemSurvey(position, data) }
        }

    }

    interface SurveyListItemListener {
        fun onClickItemSurvey(position: Int, data: Any)
    }
}