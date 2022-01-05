/* Android with clean and multi module architecture */
package com.matrix.timer.ext

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.VibrationEffect.createWaveform
import android.os.Vibrator
import com.matrix.timer.data.models.VibrationPattern

/**
 * Starts vibration.
 */
@Suppress("DEPRECATION")
fun Vibrator.vibrate(pattern: VibrationPattern) = with(pattern) {
    when {
        SDK_INT >= Build.VERSION_CODES.O -> vibrate(
            createWaveform(longArrayOf(delay, vibrate, sleep), 0)
        )
        else -> vibrate(longArrayOf(delay, vibrate, sleep), 0)
    }
}
