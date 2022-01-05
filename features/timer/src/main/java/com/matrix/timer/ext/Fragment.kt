/* Android with clean and multi module architecture */
package com.matrix.timer.ext

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.matrix.timer.R

/**
 * Set a OnBackPressedCallback.
 */
fun Fragment.setOnBackPressedCallback(block: () -> Unit) {
    val activity = requireActivity()
    activity.onBackPressedDispatcher
        .addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                remove()
                block()
                activity.onBackPressed()
            }
        })
}

/**
 * Creates a TimerDialog with the onClick callback supplied to this function.
 */
fun Fragment.timerDialog(onClick: () -> Unit) = lazy {
    with(requireContext()) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.timer_dialog_title))
            .setMessage(getString(R.string.timer_dialog_message))
            .setPositiveButton(getString(R.string.timer_dialog_button_text)) { _, _ -> onClick() }
            .create()
    }
}
