package com.app.gastranetwork.util.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.gastranetwork.R
import kotlinx.android.synthetic.main.template_progress_page.*

class ProgressDialog : DialogFragment() {

    val TAG = "FullMessageDialog"
    val ARG_TITLE = "ARG_TITLE"
    val ARG_CONTENT = "ARG_CONTENT"
    lateinit var title: String
    lateinit var content: String

    companion object {
        fun newInstance(title: String, content: String): ProgressDialog {
            var fragment = ProgressDialog()
            fragment.apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putString(ARG_CONTENT, content)
                }
            }
            return fragment
        }
    }

    override fun getTheme(): Int {
        return R.style.AppFullDialogTheme
    }

    override fun onStart() {
        super.onStart()

        if (dialog == null) {
            return
        }

        dialog?.window?.setWindowAnimations(R.style.AnimationFade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false
        var view = inflater.inflate(R.layout.template_progress_page, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initValue()
    }

    private fun initValue() {
        arguments?.getString(ARG_TITLE)?.let {
            title = if(it != "") it else "Title here"
        }

        arguments?.getString(ARG_CONTENT)?.let {
            content = if(it != "") it else "Content goes here"
        }
    }

    private fun setupView() {
        template_dialog_progress_tv_title.text = title
        template_dialog_progress_tv_content.text = content
    }

}
