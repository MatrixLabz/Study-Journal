
package com.matrix.core.domain

import com.matrix.core.domain.models.FetchResult
import com.matrix.core.domain.models.Session
import com.matrix.core.domain.models.StudyNote
import kotlinx.coroutines.flow.Flow

/**
 * Repository to perform generic CRUD operations on a database.
 */
interface CrudRepository {

    /**
     * Fetches study notes from the database.
     *
     * @return [Flow]
     */
    fun fetchStudyNotes(): Flow<FetchResult<StudyNote>>

    /**
     * Fetches sessions by study note id from the database.
     *
     * @return [Flow]
     */
    fun fetchSessionsByNoteId(noteId: Int): Flow<FetchResult<Session>>

    /**
     * Deletes a study note from the database.
     */
    suspend fun deleteStudyNote(item: StudyNote)

    /**
     * Deletes a session from the database.
     */
    suspend fun deleteSessionByNoteId(noteId: Int, item: Session)

    /**
     * Restores a study note.
     */
    suspend fun restoreStudyNote(item: StudyNote)

    /**
     * Restores a session from the database.
     */
    suspend fun restoreSessionByNoteId(noteId: Int, item: Session)

    /**
     * Adds a study note to the database.
     */
    suspend fun addStudyNote(item: StudyNote): StudyNote

    /**
     * Renames a study note.
     */
    suspend fun renameStudyNote(id: Int, title: String)

    /**
     * Saves a session to the database.
     */
    suspend fun saveSession(noteId: Int, item: Session)
}
