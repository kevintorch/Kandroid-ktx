package com.kevintorch.androidktx

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.TextView

fun TextView.afterTextChanged(block: TextView.(String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            block(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    })
}

fun TextView.onTextChanged(block: TextView.(String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            block(s.toString())
        }

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    })
}

fun TextView.beforeTextChanged(block: TextView.(String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {}

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            block(s.toString())
        }
    })
}

inline fun <reified T> AutoCompleteTextView.onSelectItem(crossinline block: AutoCompleteTextView.(T?) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            block(parent?.getItemAtPosition(position) as T?)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}

inline fun <reified T> AutoCompleteTextView.onItemClick(crossinline block: AutoCompleteTextView.(T?) -> Unit) {
    onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
        block(parent?.getItemAtPosition(position) as T?)
    }
}