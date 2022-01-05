
package com.matrix.studyNote.ui.sessionDialog

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.matrix.core.ext.hideKeyboard
import com.matrix.core.ext.onStates
import com.matrix.core.ext.toMilliseconds
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.core.utils.navigation.GlobalNavHost
import com.matrix.core.utils.navigation.navigate
import com.matrix.studyNote.R
import com.matrix.studyNote.databinding.FragmentSessionDialogBinding
import com.matrix.studyNote.domain.storage.models.UserInput
import com.matrix.studyNote.domain.validators.models.ValidationResult
import com.matrix.studyNote.ext.doOnTextChanged
import com.matrix.studyNote.ext.text
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SessionDialogFragment @Inject constructor(
    private val directions: GlobalDirections,
    private val host: GlobalNavHost
) : Fragment(R.layout.fragment_session_dialog) {
    private val args: SessionDialogFragmentArgs by navArgs()
    private val viewModel: SessionDialogViewModel by viewModels()
    private val binding: FragmentSessionDialogBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSessionFormValues()
        onSessionStateChanged()
        setTextChangedListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_session, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actionDone -> with(binding) {
            viewModel.saveSession(
                args.properties.id,
                UserInput(nameTextField.text, repsTextField.text, breakDurationField.text)
            )
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
        view?.hideKeyboard()
    }

    private fun setTextChangedListeners() = with(binding) {
        nameTextField.doOnTextChanged { nameTextField.error = null }
        repsTextField.doOnTextChanged { repsTextField.error = null }
        breakDurationField.doOnTextChanged { breakDurationField.error = null }
    }

    private fun setSessionFormValues() = with(viewModel.getUserInput()) {
        binding.nameTextField.text = name
        binding.repsTextField.text = repetitions
        binding.breakDurationField.text = breakDuration
    }

    private fun onSessionStateChanged() = onStates(viewModel) {
        when (it) {
            is SessionDialogFormStates.ValidUserInput ->
                with(binding.breakDurationField.text.toLong().toMilliseconds()) {
                    val props = args.properties.copy(breakDuration = this)
                    navigate(directions.fromSessionDialogToTimer(props), host)
                }
            is SessionDialogFormStates.InvalidInput -> renderErrorMessages(it.errors)
        }
    }

    private fun renderErrorMessages(errors: List<ValidationResult>) = errors.forEach {
        when (it) {
            is ValidationResult.InvalidName ->
                binding.nameTextField.error = getString(it.error)
            is ValidationResult.InvalidRepetitions ->
                binding.repsTextField.error = getString(it.error)
            is ValidationResult.InvalidBreakDuration ->
                binding.breakDurationField.error = getString(it.error)
        }
    }
}
