package com.app.gastranetwork.module.survey_form

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
import kotlinx.android.synthetic.main.fragment_survey_form.*

class SurveyFormFragment : BaseFragment(), SurveyFormContracts.View
    , BottomMessageDialogListener {

    var presenter: SurveyFormContracts.Presenter? = SurveyFormPresenter(this)
    var confirmCancelDialog: BottomMessageDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_form, container, false)
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
        getBaseActivity()?.changeLayoutToolbar(isToolbarLeft = true, textToolbarTitle = "Form Survey", isToolbarRight = false)

        fragment_survey_form_et_custom.setEditable(false)

        fragment_survey_form_s_custom.setOnCheckedChangeListener {
            if(it) {
                fragment_survey_form_et_custom.setEditable(true)
            } else {
                fragment_survey_form_et_custom.setText("")
                fragment_survey_form_et_custom.setEditable(false)
            }
        }

        fragment_survey_form_btn_cancel.setOnClickListener {
            confirmCancelDialog = getBaseActivity()?.showBottomDialogMessage(
                typeMessage = BottomMessageDialogType.MESSAGE_WITH_CONFIRM,
                title = "Cancel Survey",
                content = "Are you sure to cancel this survey?"
            )
            confirmCancelDialog?.listener = this
        }

        fragment_survey_form_save.setOnClickListener {
            getBaseActivity()?.isShowProgressDialog(true)
            Handler().postDelayed({
                presenter?.onSaveFormSurveyClick()
            }, AppConstants.DELAY)

        }
    }

    override fun onBottomMessageYesClicked() {
        confirmCancelDialog?.dismissWithBehavior()
        fragmentManager?.popBackStack()
        onBackPressed()
    }

    override fun onBottomMessageNoClicked() {
        confirmCancelDialog?.dismissWithBehavior()
    }

    override fun successSaveSurvey() {
        getBaseActivity()?.isShowProgressDialog(false)
        getBaseActivity()?.showFullDialogMessage(
            typeMessage = FullMessageDialogType.MESSAGE_WITH_BUTTON,
            title = "Success Save Data",
            content = "Successful save data survey"
        )
        fragmentManager?.popBackStack()
        onBackPressed()
    }

}
