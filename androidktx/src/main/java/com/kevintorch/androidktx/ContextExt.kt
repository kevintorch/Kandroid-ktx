package com.kevintorch.androidktx

import android.app.Application
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

val Context.layoutInflator get() = LayoutInflater.from(this)

inline fun <reified App : Application> Context.getApplication(): App? = applicationContext as? App

fun Context.getCompatDrawable(@DrawableRes drawable: Int): Drawable? =
    AppCompatResources.getDrawable(applicationContext, drawable)