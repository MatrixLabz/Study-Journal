/* Android with clean and multi module architecture */
package com.matrix.timer.data.models

data class VibrationPattern(
    val delay: Long = 0,
    val vibrate: Long = 1000,
    val sleep: Long = 1000
)
