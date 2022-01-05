/* Android with clean and multi module architecture */
package com.matrix.core.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Pop up a quick message to undo the last database operation.
 */
fun showUndoMessage(view: View, text: String, actionText: String = "Undo", action: (View) -> Unit) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
        .setAction(actionText, action)
        .show()
}
