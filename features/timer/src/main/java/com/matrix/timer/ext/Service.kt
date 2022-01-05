/* Android with clean and multi module architecture */
package com.matrix.timer.ext

import android.app.Service
import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Vibrator
import android.os.VibratorManager

/**
 * Returns the default Vibrator for the device.
 */
@Suppress("DEPRECATION")
val Service.defaultVibrator
    get() = when {
        SDK_INT >= Build.VERSION_CODES.S -> {
            val manager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            manager.defaultVibrator
        }
        else -> getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
    }
