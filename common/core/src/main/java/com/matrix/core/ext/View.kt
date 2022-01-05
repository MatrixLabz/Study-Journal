/* Android with clean and multi module architecture */
package com.matrix.core.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Hides the on-screen keyboard window.
 */
fun View.hideKeyboard() =
    with(context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager) {
        hideSoftInputFromWindow(windowToken, 0)
    }
