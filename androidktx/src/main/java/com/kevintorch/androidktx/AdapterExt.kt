package com.kevintorch.androidktx

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

fun <T> Context.arrayAdapter(
    @LayoutRes resource: Int = -1,
    @IdRes textViewResourceId: Int = 0,
    objects: MutableList<T> = mutableListOf(),
    idSelector: (T?, position: Int) -> Long = { _, position -> position.toLong() },
    viewCreator: (ArrayAdapter<T>.(position: Int, convertView: View?, parent: ViewGroup) -> View)? = null,
): ArrayAdapter<T> {
    return object : ArrayAdapter<T>(this, resource, textViewResourceId, objects) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return if (viewCreator != null) {
                viewCreator(position, convertView, parent)
            } else {
                super.getView(position, convertView, parent)
            }
        }

        override fun getItemId(position: Int): Long {
            return idSelector(getItem(position), position)
        }
    }
}