package com.app.gastranetwork.util.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.app.gastranetwork.R
import com.app.gastranetwork.util.extension.setShow
import com.github.heyalex.bottomdrawer.BottomDrawerDialog
import com.github.heyalex.bottomdrawer.BottomDrawerFragment
import com.github.heyalex.handle.PlainHandleView
import kotlinx.android.synthetic.main.template_dialog_bottom.*

enum class BottomMessageDialogType {
    MESSAGE_ONLY,
    MESSAGE_WITH_BUTTON,
    MESSAGE_WITH_CONFIRM
}

interface BottomMessageDialogListener {
    fun onBottomMessageYesClicked()
    fun onBottomMessageNoClicked()
}

class BottomMessageDialog: BottomDrawerFragment() {

    val TAG = "BottomMessageDialog"
    val ARG_TYPE = "ARG_TYPE"
    val ARG_TITLE = "ARG_TITLE"
    val ARG_CONTENT = "ARG_CONTENT"
    val ARG_IMG = "ARG_IMG"

    lateinit var listener: BottomMessageDialogListener
    lateinit var messageDialogType: BottomMessageDialogType
    lateinit var title: String
    lateinit var content: String
    var img: Int = 0

    companion object {
        fun newInstance(messageDialogType: BottomMessageDialogType
                        , img: Int
                        , title: String
                        , content: String
        ) : BottomMessageDialog {
            var fragment = BottomMessageDialog()
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initValue()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        var view = inflater.inflate(R.layout.template_dialog_bottom, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun configureBottomDrawer(): BottomDrawerDialog {
        return BottomDrawerDialog.build(context!!) {
            theme = R.style.AppBottomDialogTheme
            //configure handle view
            handleView = PlainHandleView(context).apply {
                val widthHandle =
                    resources.getDimensionPixelSize(R.dimen.bottom_sheet_handle_width)
                val heightHandle =
                    resources.getDimensionPixelSize(R.dimen.bottom_sheet_handle_height)
                val params =
                    FrameLayout.LayoutParams(widthHandle, heightHandle, Gravity.CENTER_HORIZONTAL)

                params.topMargin =
                    resources.getDimensionPixelSize(R.dimen.bottom_sheet_handle_top_margin)

                layoutParams = params
            }
        }
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
            messageDialogType = (it as BottomMessageDialogType)
        }
    }

    private fun setupView() {
        template_dialog_bottom_message_tv_title.text = title
        template_dialog_bottom_message_tv_content.text = content
        template_dialog_bottom_message_iv_image.setImageResource(img)

        buttonSetup()
    }

    private fun buttonSetup() {
        when (messageDialogType) {
            BottomMessageDialogType.MESSAGE_ONLY -> {
                template_dialog_bottom_message_container_btn.setShow(false)
            }

            BottomMessageDialogType.MESSAGE_WITH_BUTTON -> {
                template_dialog_bottom_message_btn_no.setShow(false)
            }

            else -> {
                // CONFIRM
            }
        }

        template_dialog_bottom_message_btn_yes.setOnClickListener {
            listener.onBottomMessageYesClicked()
        }

        template_dialog_bottom_message_btn_no.setOnClickListener {
            listener.onBottomMessageNoClicked()
        }
    }
}
