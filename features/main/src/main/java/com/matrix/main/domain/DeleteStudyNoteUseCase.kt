/* Android with clean and multi module architecture */
package com.matrix.main.domain

import com.matrix.core.domain.CrudRepository
import com.matrix.core.domain.models.StudyNote
import javax.inject.Inject

/**
 * Deletes a study note from the database.
 */
class DeleteStudyNoteUseCase @Inject constructor(
    private val repository: CrudRepository
) {
    suspend operator fun invoke(studyNote: StudyNote) =
        repository.deleteStudyNote(studyNote)
}
