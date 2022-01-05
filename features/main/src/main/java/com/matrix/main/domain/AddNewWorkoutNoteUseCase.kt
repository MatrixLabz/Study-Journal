/* Android with clean and multi module architecture */
package com.matrix.main.domain

import com.matrix.core.domain.CrudRepository
import com.matrix.core.domain.models.StudyNote
import com.matrix.core.ext.format
import java.util.Date
import javax.inject.Inject

/**
 * Adds a new workout note to the database.
 */
class AddNewWorkoutNoteUseCase @Inject constructor(
    private val repository: CrudRepository
) {
    suspend operator fun invoke() =
        repository.addStudyNote(StudyNote(createdAt = Date().format()))
}
