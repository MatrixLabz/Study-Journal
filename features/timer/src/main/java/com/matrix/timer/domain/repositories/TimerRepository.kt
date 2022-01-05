/* Android with clean and multi module architecture */
package com.matrix.timer.domain.repositories

/**
 * Repository for performing countdown timer operations.
 */
interface TimerRepository {
    /**
     * Subscribes to a countdown timer state changes.
     */
    suspend fun subscribe(onTick: (Long) -> Unit)

    /**
     * Starts a timer service with the given break duration.
     */
    fun startService(breakDuration: Long)

    /**
     * Stops a timer service.
     */
    fun stopService()
}
