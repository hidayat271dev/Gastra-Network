package com.app.gastranetwork.base

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment(), BaseContracts.View {

    open fun onBackPressed(): Boolean {
        return false
    }

    fun getBaseActivity(): BaseActivity? {
        var context = context
        while (context is ContextWrapper) {
            if (context is BaseActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    override fun getActivityContext(): Context? {
        return getBaseActivity()
    }
}
