/* Android with clean and multi module architecture */
package com.matrix.core.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matrix.core.domain.models.Session

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val noteId: Int,
    val name: String,
    val repetitions: String,
    val breakDuration: Long
)

/**
 * Maps the [SessionEntity] to the [Session].
 */
fun SessionEntity.toSession() = Session(
    id = id,
    name = name,
    repetitions = repetitions,
    breakDuration = breakDuration
)

/**
 * Maps the [Session] to the [SessionEntity].
 */
fun Session.toSessionEntity(noteId: Int) = SessionEntity(
    id = id,
    noteId = noteId,
    name = name,
    repetitions = repetitions,
    breakDuration = breakDuration
)
