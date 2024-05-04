package com.kevintorch.androidktx

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.core.util.TypedValueCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet.ORDERING_TOGETHER
import com.google.android.material.textfield.TextInputLayout

fun View.animate(handler: View.() -> Unit) {
    val animation = AutoTransition().setOrdering(ORDERING_TOGETHER)
    val p = findViewGroup()
    if (p != null) {
        TransitionManager.beginDelayedTransition(p, animation)
    }
    handler()
}

fun View.findViewGroup(): ViewGroup? {
    val p = parent ?: return null
    return if (p is ViewGroup) p else if (p is View) p.findViewGroup() else null
}

fun View.hideKeyboard(useWindowInsetsController: Boolean = true) {
    if (useWindowInsetsController) {
        windowInsetsControllerCompat?.hide(WindowInsetsCompat.Type.ime())
        return
    }
    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showKeyboard(useWindowInsetsController: Boolean = true) {
    if (useWindowInsetsController) {
        windowInsetsControllerCompat?.show(WindowInsetsCompat.Type.ime())
        return
    }
    inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.requestFocusAndShowKeyboard(useWindowInsetsController: Boolean = true) {
    requestFocus()
    post { showKeyboard(useWindowInsetsController) }
}

fun View.clearFocusAndHideKeyboard(useWindowInsetsController: Boolean = true) {
    clearFocus()
    post { hideKeyboard(useWindowInsetsController) }
}

fun View.applyWindowInsetsPadding() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v: View, insets: WindowInsetsCompat ->
        val sysInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(sysInsets.left, sysInsets.top, sysInsets.right, sysInsets.bottom)
        WindowInsetsCompat.CONSUMED
    }
}

fun View.removeFromParent(animated: Boolean = false) {
    if (parent == null) return
    val p = parent
    if (p is ViewGroup) {
        val removeView: View.() -> Unit = { p.removeView(this) }
        if (animated) animate(removeView) else removeView()
    }
}

val View.layoutInflator get() = context.layoutInflator

val View.inputMethodManager: InputMethodManager?
    get() = ContextCompat.getSystemService(context, InputMethodManager::class.java)

val View.windowInsetsControllerCompat: WindowInsetsControllerCompat?
    get() = ViewCompat.getWindowInsetsController(this)

//fun TextInputLayout.loadingAppearance(loading: Boolean) {
//    val circularProgressDrawable = CircularProgressDrawables.ofTextInputLayout(context)
//    isEnabled = !loading
//    endIconMode =
//        if (loading) TextInputLayout.END_ICON_CUSTOM else TextInputLayout.END_ICON_DROPDOWN_MENU
//    if (loading) {
//        endIconDrawable = circularProgressDrawable
//        circularProgressDrawable.start()
//    }
//}

fun <T : View> T.doPost(action: T.() -> Unit) {
    post { action() }
}

val TextInputLayout.autoCompleteTextView: AutoCompleteTextView? get() = editText as? AutoCompleteTextView

fun View.padding(
    horizontal: Int = paddingStart,
    vertical: Int = paddingTop
) {
    setPadding(horizontal, vertical, horizontal, vertical)
}

fun View.padding(
    left: Int = paddingStart,
    top: Int = paddingTop,
    right: Int = paddingEnd,
    bottom: Int = paddingBottom
) {
    setPadding(left, top, right, bottom)
}

val Number.dp
    get() = TypedValueCompat.pxToDp(this.toFloat(), Resources.getSystem().displayMetrics)
        .toInt()