package com.app.gastranetwork.util.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.gastranetwork.R
import com.app.gastranetwork.util.extension.setShow
import kotlinx.android.synthetic.main.template_dialog_full.*

enum class FullMessageDialogType {
    MESSAGE_ONLY,
    MESSAGE_ONLY_NO_CANCEL,
    MESSAGE_WITH_BUTTON,
    MESSAGE_WITH_CONFIRM
}

interface FullMessageDialogListener {
    fun onFullMessageYesClicked()
    fun onFullMessageNoClicked()
}

class FullMessageDialog : DialogFragment() {

    val TAG = "FullMessageDialog"
    val ARG_TYPE = "ARG_TYPE"
    val ARG_TITLE = "ARG_TITLE"
    val ARG_CONTENT = "ARG_CONTENT"
    val ARG_IMG = "ARG_IMG"

    lateinit var listener: FullMessageDialogListener
    lateinit var dialogType: FullMessageDialogType
    lateinit var title: String
    lateinit var content: String
    var img: Int = 0

    companion object {
        fun newInstance( messageDialogType: FullMessageDialogType
            , img: Int
            , title: String
            , content: String
        ): FullMessageDialog {
            var fragment = FullMessageDialog()
            fragment.apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_TYPE, messageDialogType)
                    putString(ARG_TITLE, title)
                    putString(ARG_CONTENT, content)
                    putInt(ARG_IMG, img)
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
        var view = inflater.inflate(R.layout.template_dialog_full, container, false)
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

        arguments?.getInt(ARG_IMG)?.let {
            img = if(it != 0) it else R.drawable.ic_placeholderimage
        }

        arguments?.getSerializable(ARG_TYPE)?.let {
            dialogType = (it as FullMessageDialogType)
        }
    }

    private fun setupView() {
        template_dialog_full_tv_title.text = title
        template_dialog_full_tv_content.text = content
        template_dialog_full_iv_image.setImageResource(img)

        buttonSetup()
    }

    private fun buttonSetup() {
        when (dialogType) {
            FullMessageDialogType.MESSAGE_ONLY -> {
                template_dialog_full_container_btn.setShow(false)
            }

            FullMessageDialogType.MESSAGE_WITH_BUTTON -> {
                template_dialog_full_btn_no.setShow(false)
            }

            FullMessageDialogType.MESSAGE_ONLY_NO_CANCEL -> {
                template_dialog_full_iv_cancel.setShow(false)
                template_dialog_full_container_btn.setShow(false)
            }

            else -> {
                // CONFIRM
            }
        }

        template_dialog_full_btn_yes.setOnClickListener {
            listener.onFullMessageYesClicked()
        }

        template_dialog_full_btn_no.setOnClickListener {
            listener.onFullMessageNoClicked()
        }

        template_dialog_full_iv_cancel.setOnClickListener {
            dismissAllowingStateLoss()
        }
    }
}
