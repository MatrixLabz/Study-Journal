/* Android with clean and multi module architecture */
package com.matrix.timer.data.services

import android.app.Notification
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.PowerManager
import com.matrix.timer.data.models.VibrationPattern
import com.matrix.timer.ext.defaultVibrator
import com.matrix.timer.ext.vibrate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@AndroidEntryPoint
class TimerService : Service() {
    @Inject
    lateinit var notification: Notification

    private var timer: Timer? = null
    private val binder = TimerBinder()
    private val state = MutableStateFlow(Long.MIN_VALUE)

    private val wakeLock by lazy {
        (getSystemService(Context.POWER_SERVICE) as PowerManager)
            .newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TIMER_WAKE_LOCK_TAG)
    }

    val timerState: StateFlow<Long> = state

    companion object {
        private const val TIMER_OFFSET = 1000L
        private const val TIMER_WAKE_LOCK_TIMEOUT = 10 * 60 * 1000L
        private const val TIMER_WAKE_LOCK_TAG = "studyjournal:timer_wake_lock"

        const val NOTIFICATION_ID = 1
        const val TIME_IS_UP = 0L
        const val STOP_SHORT_BREAK = "stop_short_break"
        const val START_SHORT_BREAK = "start_short_break"
        const val TIMER_DURATION_IN_MILLIS = "timer_duration_in_millis"
    }

    override fun onBind(intent: Intent?) = binder

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        when (intent.action) {
            START_SHORT_BREAK -> start(intent)
            else -> stop()
        }
        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stop()
    }

    private fun start(intent: Intent) {
        val breakDuration = intent.getLongExtra(TIMER_DURATION_IN_MILLIS, 0L)
        timer = Timer(duration = breakDuration, interval = 500L)
        startForeground(NOTIFICATION_ID, notification)
        timer?.start()
        wakeLock.acquire(TIMER_WAKE_LOCK_TIMEOUT)
    }

    private fun stop() {
        timer?.cancel()
        removeNotification()
        defaultVibrator.cancel()
        stopForeground(true)
        if (wakeLock.isHeld) wakeLock.release()
        stopSelf()
    }

    private fun removeNotification() {
        val manager = (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.cancel(NOTIFICATION_ID)
    }

    inner class TimerBinder : Binder() {
        val service get() = this@TimerService
    }

    inner class Timer(duration: Long, interval: Long) : CountDownTimer(duration, interval) {
        override fun onTick(millisUntilFinished: Long) {
            state.value = millisUntilFinished + TIMER_OFFSET
        }

        override fun onFinish() {
            defaultVibrator.vibrate(VibrationPattern())
            state.value = TIME_IS_UP
        }
    }
}
