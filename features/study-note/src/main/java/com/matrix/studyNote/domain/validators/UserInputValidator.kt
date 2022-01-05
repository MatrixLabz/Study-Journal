/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.validators

import com.matrix.studyNote.R
import com.matrix.studyNote.domain.storage.models.UserInput
import com.matrix.studyNote.domain.validators.models.ValidationResult
import javax.inject.Inject

/**
 * Validates a user input.
 */
class UserInputValidator @Inject constructor() {
    private val validations = setOf(NameValidation, RepetitionsValidation, BreakDurationValidation)

    fun validate(input: UserInput) = validations.filter { !it(input) }.map(::toValidationResult)

    private fun toValidationResult(validation: Validation) = when (validation) {
        is NameValidation ->
            ValidationResult.InvalidName(R.string.invalid_name_format)
        is RepetitionsValidation ->
            ValidationResult.InvalidRepetitions(R.string.invalid_repetitions_format)
        is BreakDurationValidation ->
            ValidationResult.InvalidBreakDuration(R.string.invalid_break_duration_format)
    }
}
