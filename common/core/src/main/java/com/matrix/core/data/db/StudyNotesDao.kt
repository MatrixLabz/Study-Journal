/* Android with clean and multi module architecture */
package com.matrix.core.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.core.data.db.models.StudyNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StudyNotesDao {
    @Query("SELECT * FROM study_notes")
    fun fetchStudyNotes(): Flow<List<StudyNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveStudyNote(studyNoteEntity: StudyNoteEntity): Long

    @Query("UPDATE study_notes SET title = :title WHERE id = :id")
    suspend fun updateStudyNote(id: Int, title: String)

    @Delete
    suspend fun removeStudyNotes(studyNoteEntity: StudyNoteEntity)

    @Query("SELECT * FROM study_notes WHERE id = :noteId")
    suspend fun fetchStudyNoteById(noteId: Int): StudyNoteEntity
}
