package com.matrix.timer.data.repositories

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.matrix.timer.data.services.TimerService
import com.matrix.timer.data.services.TimerService.Companion.START_SHORT_BREAK
import com.matrix.timer.data.services.TimerService.Companion.STOP_SHORT_BREAK
import com.matrix.timer.domain.repositories.TimerRepository
import com.matrix.timer.ext.sendCommandToService
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

/**
 * Default implementation of the [TimerRepository].
 */
class DefaultTimerRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : TimerRepository {
    private val timerState = MutableStateFlow<StateFlow<Long>?>(null)

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            timerState.value = (service as TimerService.TimerBinder).service.timerState
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            timerState.value = null
        }
    }

    override suspend fun subscribe(onTick: (Long) -> Unit) {
        timerState.collectLatest { it?.collectLatest(onTick) }
    }

    override fun startService(breakDuration: Long) {
        with(Intent(context, TimerService::class.java)) {
            val extra = TimerService.TIMER_DURATION_IN_MILLIS to breakDuration
            context.sendCommandToService(this, START_SHORT_BREAK, extra)
            context.bindService(this, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun stopService() {
        context.sendCommandToService(Intent(context, TimerService::class.java), STOP_SHORT_BREAK)
        context.unbindService(connection)
    }
}
