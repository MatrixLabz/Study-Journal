/* Android with clean and multi module architecture */
package com.matrix.timer.ext

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat

/**
 * Sends a command to foreground service.
 */
fun Context.sendCommandToService(
    intent: Intent,
    action: String,
    extra: Pair<String, Long>? = null
) {
    intent.action = action
    extra?.let { intent.putExtra(it.first, it.second) }
    ContextCompat.startForegroundService(this, intent)
}
