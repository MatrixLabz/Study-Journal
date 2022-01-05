/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.storage.models

import com.matrix.core.domain.models.Session
import com.matrix.core.ext.toMilliseconds

data class UserInput(val name: String, val repetitions: String, val breakDuration: String)

/**
 * Maps [UserInput] to [Session].
 */
fun UserInput.toSession() = Session(
    id = 0,
    name = name,
    repetitions = repetitions,
    breakDuration = breakDuration.toLong().toMilliseconds()
)
