package com.app.gastranetwork.util.extension

import android.view.View

internal fun View.setShow(show: Boolean) {
    if(show) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}