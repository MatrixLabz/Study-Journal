/* Android with clean and multi module architecture */
package com.matrix.main.domain

import com.matrix.core.domain.CrudRepository
import com.matrix.core.domain.models.StudyNote
import javax.inject.Inject

/**
 * Restores the last deleted workout note from the database.
 */
class RestoreStudyNoteUseCase @Inject constructor(private val repository: CrudRepository) {
    suspend operator fun invoke(workoutNote: StudyNote) =
        repository.restoreStudyNote(workoutNote)
}
