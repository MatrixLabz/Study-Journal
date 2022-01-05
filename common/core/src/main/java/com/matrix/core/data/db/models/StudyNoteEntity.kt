/* Android with clean and multi module architecture */
package com.matrix.core.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matrix.core.domain.models.StudyNote

@Entity(tableName = "study_notes")
data class StudyNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val createdAt: String
)

/**
 * Maps the [StudyNoteEntity] to the [StudyNote].
 */
fun StudyNoteEntity.toWorkoutNote() = StudyNote(
    id = id,
    title = title,
    createdAt = createdAt
)

/**
 * Maps the [StudyNote] to the [StudyNoteEntity].
 */
fun StudyNote.toWorkoutNoteEntity() = StudyNoteEntity(
    id = id,
    title = title,
    createdAt = createdAt
)
