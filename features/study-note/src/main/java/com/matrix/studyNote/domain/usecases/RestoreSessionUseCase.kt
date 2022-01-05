/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.usecases

import com.matrix.core.domain.CrudRepository
import com.matrix.core.domain.models.Session
import javax.inject.Inject

/**
 * Restores the last deleted session in the database.
 */
class RestoreSessionUseCase @Inject constructor(private val repository: CrudRepository) {
    suspend operator fun invoke(noteId: Int, session: Session) =
        repository.restoreSessionByNoteId(noteId, session)
}
