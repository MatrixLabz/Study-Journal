/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.usecases

import com.matrix.core.domain.CrudRepository
import javax.inject.Inject

/**
 * Renames a workout note in the database by id.
 */
class RenameWorkoutUseCase @Inject constructor(private val repository: CrudRepository) {
    suspend operator fun invoke(noteId: Int, title: String) =
        repository.renameStudyNote(noteId, title)
}
