/* Android with clean and multi module architecture */
package com.matrix.timer.domain.interactors

import android.content.SharedPreferences
import com.matrix.core.di.DefaultSharedPreferences
import com.matrix.timer.domain.models.ExecutionMode
import com.matrix.timer.domain.repositories.TimerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TimerInteractor @Inject constructor(
    @DefaultSharedPreferences private val sharedPreferences: SharedPreferences,
    private val repository: TimerRepository
) {

    companion object {
        /**
         * Determines whether a countdown timer should start automatically.
         */
        private const val START_BREAKS_AUTOMATICALLY = "start_breaks_automatically"
    }

    /**
     * Collects the latest countdown timer state.
     */
    suspend fun collectTimerState(action: (Long) -> Unit) {
        repository.subscribe(action)
    }

    /**
     * Starts a countdown timer for a short break.
     */
    fun startShortBreak(breakDuration: Long) {
        repository.startService(breakDuration)
    }

    /**
     * Stops a countdown timer for a short break.
     */
    fun stopShortBreak() {
        repository.stopService()
    }

    /**
     * Reads countdown timer execution mode from the storage.
     */
    fun readExecutionMode() = flowOf(
        when (sharedPreferences.getBoolean(START_BREAKS_AUTOMATICALLY, false)) {
            true -> ExecutionMode.SelfExecuting
            else -> ExecutionMode.Manual
        }
    ).flowOn(Dispatchers.IO)
}
