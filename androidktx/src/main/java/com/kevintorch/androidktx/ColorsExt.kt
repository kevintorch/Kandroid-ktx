package com.kevintorch.androidktx

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

@ColorInt
fun Context.getColorInt(@ColorRes color: Int): Int = ContextCompat.getColor(this, color)

fun Int.toColorDrawable(): ColorDrawable = ColorDrawable(this)

fun Context.colorDrawableFrom(@ColorRes color: Int): ColorDrawable =
    getColorInt(color).toColorDrawable()

fun Context.getColorStateList(color: Int): ColorStateList? =
    ResourcesCompat.getColorStateList(resources, color, theme)

@ColorInt
fun Context.getColorAttr(@AttrRes colorAttr: Int): Int {
    val a = obtainStyledAttributes(null, intArrayOf(colorAttr))
    try {
        return a.getColor(0, 0)
    } finally {
        a.recycle()
    }
}
