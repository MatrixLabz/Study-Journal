package com.matrix.studyNote.ui.sessionDialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.core.ui.StateOwner
import com.matrix.studyNote.domain.storage.models.UserInput
import com.matrix.studyNote.domain.usecases.GetUserInputUseCase
import com.matrix.studyNote.domain.usecases.SaveSessionUseCase
import com.matrix.studyNote.domain.validators.UserInputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionDialogViewModel @Inject constructor(
    private val saveSessionUseCase: SaveSessionUseCase,
    private val getUserInputUseCase: GetUserInputUseCase,
    private val validator: UserInputValidator
) : ViewModel(), StateOwner<SessionDialogState> {
    private val _state = MutableStateFlow<SessionDialogState>(SessionDialogState.Empty)
    override val state: StateFlow<SessionDialogState> get() = _state

    fun saveSession(noteId: Int, userInput: UserInput) =
        validator.validate(userInput).also {
            _state.value = SessionDialogState.Empty
            when {
                it.isEmpty() -> {
                    viewModelScope.launch { saveSessionUseCase(noteId, userInput) }
                    _state.value = SessionDialogFormStates.ValidUserInput
                }
                else -> _state.value = SessionDialogFormStates.InvalidInput(it)
            }
        }

    fun getUserInput() = getUserInputUseCase()
}
