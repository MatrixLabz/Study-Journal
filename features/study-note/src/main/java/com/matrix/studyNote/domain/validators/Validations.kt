/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.validators

import com.matrix.studyNote.domain.storage.models.UserInput

/**
 * Base class for validations.
 */
internal sealed interface Validation {
    operator fun invoke(input: UserInput): Boolean
}

/**
 * Validates name. A valid name should have not to be empty.
 */
object NameValidation : Validation {
    override fun invoke(input: UserInput): Boolean = input.name.isNotEmpty()
}

/**
 * Validates repetitions. Valid repetitions should have only positive integers.
 */
object RepetitionsValidation : Validation {
    override fun invoke(input: UserInput): Boolean =
        "^[1-9]\\d*\$".toRegex().matches(input.repetitions)
}

/**
 * Validates a break duration. A valid break duration should have only positive integers.
 */
object BreakDurationValidation : Validation {
    override fun invoke(input: UserInput): Boolean =
        "^[1-9]\\d*\$".toRegex().matches(input.breakDuration)
}
