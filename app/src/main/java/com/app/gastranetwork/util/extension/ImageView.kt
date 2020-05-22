package com.app.gastranetwork.util.extension

import android.widget.ImageView
import androidx.core.content.ContextCompat

internal fun ImageView.setImageWithColor(picture: Int, color: Int) {
    this.setImageResource(picture)
    this.setColorFilter(ContextCompat.getColor(context, color))
}