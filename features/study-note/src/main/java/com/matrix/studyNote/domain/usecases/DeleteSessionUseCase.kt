
package com.matrix.studyNote.domain.usecases

import com.matrix.core.domain.CrudRepository
import com.matrix.core.domain.models.Session
import javax.inject.Inject

/**
 * Deletes a session from the database.
 */
class DeleteSessionUseCase @Inject constructor(private val repository: CrudRepository) {
    suspend operator fun invoke(noteId: Int, session: Session) =
        repository.deleteSessionByNoteId(noteId, session)
}
