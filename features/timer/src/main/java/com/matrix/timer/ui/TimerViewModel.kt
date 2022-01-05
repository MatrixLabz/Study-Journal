/* Android with clean and multi module architecture */
package com.matrix.timer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.core.ui.StateOwner
import com.matrix.timer.domain.interactors.TimerInteractor
import com.matrix.timer.domain.models.ExecutionMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(
    private val interactor: TimerInteractor
) : ViewModel(), StateOwner<ExecutionMode> {
    override val state = interactor.readExecutionMode()
        .stateIn(viewModelScope, SharingStarted.Lazily, ExecutionMode.Manual)

    fun collectTimerState(action: (Long) -> Unit) {
        viewModelScope.launch { interactor.collectTimerState(action) }
    }

    fun startShortBreak(breakDuration: Long) {
        interactor.startShortBreak(breakDuration)
    }

    fun stopShortBreak() {
        interactor.stopShortBreak()
    }
}
