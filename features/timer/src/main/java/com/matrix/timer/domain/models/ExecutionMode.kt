/* Android with clean and multi module architecture */
package com.matrix.timer.domain.models

/**
 * Countdown timer execution modes.
 */
sealed class ExecutionMode {
    object Manual : ExecutionMode()
    object SelfExecuting : ExecutionMode()
}
