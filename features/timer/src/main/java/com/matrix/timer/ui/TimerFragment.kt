/* Android with clean and multi module architecture */
package com.matrix.timer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.button.MaterialButton
import com.matrix.core.ext.onStates
import com.matrix.core.ext.setActionBarTitle
import com.matrix.core.ext.toFormattedTime
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.core.utils.navigation.GlobalNavHost
import com.matrix.core.utils.navigation.navigate
import com.matrix.timer.R
import com.matrix.timer.data.services.TimerService.Companion.TIME_IS_UP
import com.matrix.timer.databinding.FragmentTimerBinding
import com.matrix.timer.domain.models.ExecutionMode
import com.matrix.timer.ext.setOnBackPressedCallback
import com.matrix.timer.ext.timerDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TimerFragment @Inject constructor(
    private val directions: GlobalDirections, private val host: GlobalNavHost
) : Fragment(R.layout.fragment_timer) {

    private val args: TimerFragmentArgs by navArgs()
    private val viewModel: TimerViewModel by viewModels()
    private val binding: FragmentTimerBinding by viewBinding()

    private val dialog = timerDialog(onClick = {
        viewModel.stopShortBreak()
        navigate(directions.fromTimerToStudyNote(args.properties.copy()), host)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBarTitle(args.properties.title)
        setOnBackPressedCallback(::onBackPressed)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onReadTimerExecutionMode()
        binding.timerButton.addOnCheckedChangeListener(::onChecked)
        binding.timerView.text = args.properties.breakDuration.toFormattedTime()
        viewModel.collectTimerState(::renderTimerState)
    }

    override fun onDestroy() {
        super.onDestroy()
        dialog.value.dismiss()
    }

    private fun onReadTimerExecutionMode() = onStates(viewModel) {
        if (it is ExecutionMode.SelfExecuting) {
            binding.timerButton.isChecked = true
        }
    }

    private fun onBackPressed() {
        if (binding.timerButton.isChecked) {
            viewModel.stopShortBreak()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onChecked(button: MaterialButton, isChecked: Boolean) = when {
        isChecked -> {
            viewModel.startShortBreak(args.properties.breakDuration)
            binding.timerButton.text = getString(R.string.stop_button_text)
        }
        else -> {
            viewModel.stopShortBreak()
            navigate(directions.fromTimerToStudyNote(args.properties.copy()), host)
        }
    }

    private fun renderTimerState(state: Long) {
        when (state) {
            TIME_IS_UP -> {
                dialog.value.show()
                binding.timerView.text = getString(R.string.timer_is_up)
            }
            else -> binding.timerView.text = state.toFormattedTime()
        }
    }
}
