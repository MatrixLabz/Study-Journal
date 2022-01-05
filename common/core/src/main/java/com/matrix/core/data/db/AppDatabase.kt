/* Android with clean and multi module architecture */
package com.matrix.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matrix.core.data.db.models.SessionEntity
import com.matrix.core.data.db.models.StudyNoteEntity

@Database(
    entities = [SessionEntity::class, StudyNoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionsDao(): SessionsDao
    abstract fun studyNotesDao(): StudyNotesDao
}
