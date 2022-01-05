package com.matrix.studyNote.ui.sessionDialog

import com.matrix.studyNote.domain.validators.models.ValidationResult

open class SessionDialogState {
    object Empty: SessionDialogState()
}

sealed class SessionDialogFormStates: SessionDialogState() {
    object ValidUserInput: SessionDialogFormStates()
    data class InvalidInput(val errors: List<ValidationResult>): SessionDialogFormStates()
}
