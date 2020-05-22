package com.app.gastranetwork.module.survey_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.app.gastranetwork.R
import com.app.gastranetwork.base.BaseFragment
import com.app.gastranetwork.module.survey_list.adapter.SurveyListItemAdapter
import kotlinx.android.synthetic.main.fragment_survey_list.*

class SurveyListFragment : BaseFragment(), SurveyListContracts.View
    , SurveyListItemAdapter.SurveyListItemListener {

    var presenter: SurveyListContracts.Presenter? = SurveyListPresenter(this)
    var adapterItemSurvey = SurveyListItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_list, container, false)
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
        getBaseActivity()?.changeLayoutToolbar(isToolbarLeft = true, textToolbarTitle = "List Survey", isToolbarRight = false)

        adapterItemSurvey.listener = this
        fragment_survey_list_rv_data.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        fragment_survey_list_rv_data.adapter = adapterItemSurvey

        for (i in 0 until 15) {
            adapterItemSurvey.addData("")
        }

        fragment_survey_list_btn_add.setOnClickListener {
            presenter?.onBtnAddClicked()
        }
    }

    override fun onClickItemSurvey(position: Int, data: Any) {
        Log.d("onClickItem", "Position ${position} ${data.toString()}")
    }

}
