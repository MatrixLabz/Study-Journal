/* Android with clean and multi module architecture */
package com.matrix.core.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matrix.core.data.db.models.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionsDao {
    @Query("SELECT * FROM sessions WHERE noteId =:noteId")
    fun fetchSessions(noteId: Int): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessions WHERE noteId =:noteId")
    fun fetchSessionsById(noteId: Int): List<SessionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(sessionEntity: SessionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSessions(sessionEntity: List<SessionEntity>)

    @Delete
    suspend fun removeSession(sessionEntity: SessionEntity)

    @Query("DELETE FROM sessions WHERE noteID = :noteId")
    suspend fun removeSessions(noteId: Int)
}
