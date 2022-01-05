/* Android with clean and multi module architecture */
package com.matrix.core.data.repositories

import com.matrix.core.data.db.AppDatabase
import com.matrix.core.data.db.models.SessionEntity
import com.matrix.core.data.db.models.toSession
import com.matrix.core.data.db.models.toSessionEntity
import com.matrix.core.data.db.models.toWorkoutNote
import com.matrix.core.data.db.models.toWorkoutNoteEntity
import com.matrix.core.domain.models.FetchResult
import com.matrix.core.domain.models.Session
import com.matrix.core.domain.models.StudyNote
import com.matrix.core.domain.CrudRepository
import com.matrix.core.utils.execute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Default implementation of the [CrudRepository] interface.
 */
class DefaultCrudRepository @Inject constructor(
    database: AppDatabase
) : CrudRepository {
    private var cashed = listOf<SessionEntity>()
    private val sessionsDao = database.sessionsDao()
    private val workoutNotesDao = database.studyNotesDao()

    override fun fetchStudyNotes() = workoutNotesDao.fetchStudyNotes()
        .map { list ->
            when {
                list.isEmpty() -> FetchResult.Empty
                else -> FetchResult.Result(list.map { it.toWorkoutNote() })
            }
        }

    override fun fetchSessionsByNoteId(noteId: Int) = sessionsDao.fetchSessions(noteId)
        .map { list ->
            when {
                list.isEmpty() -> FetchResult.Empty
                else -> FetchResult.Result(list.map { it.toSession() })
            }
        }

    override suspend fun deleteStudyNote(item: StudyNote) = execute {
        cashed = sessionsDao.fetchSessionsById(item.id)
        workoutNotesDao.removeStudyNotes(item.toWorkoutNoteEntity())
        sessionsDao.removeSessions(item.id)
    }

    override suspend fun addStudyNote(item: StudyNote) = withContext(Dispatchers.IO) {
        val id = workoutNotesDao.saveStudyNote(item.toWorkoutNoteEntity())
        val note = workoutNotesDao.fetchStudyNoteById(id.toInt())
        StudyNote(note.id, note.title, note.createdAt)
    }

    override suspend fun restoreStudyNote(item: StudyNote) = execute {
        sessionsDao.saveSessions(cashed)
        workoutNotesDao.saveStudyNote(item.toWorkoutNoteEntity())
    }

    override suspend fun deleteSessionByNoteId(noteId: Int, item: Session) = execute {
        sessionsDao.removeSession(item.toSessionEntity(noteId))
    }

    override suspend fun restoreSessionByNoteId(noteId: Int, item: Session) = execute {
        sessionsDao.saveSession(item.toSessionEntity(noteId))
    }

    override suspend fun renameStudyNote(id: Int, title: String) = execute {
        workoutNotesDao.updateStudyNote(id, title)
    }

    override suspend fun saveSession(noteId: Int, item: Session) = execute {
        sessionsDao.saveSession(item.toSessionEntity(noteId))
    }
}
