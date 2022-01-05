/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.validators.models

import androidx.annotation.StringRes

/**
 * Provides validation result' types.
 */
sealed class ValidationResult {
    data class InvalidName(@StringRes val error: Int) : ValidationResult()
    data class InvalidRepetitions(@StringRes val error: Int) : ValidationResult()
    data class InvalidBreakDuration(@StringRes val error: Int) : ValidationResult()
}
