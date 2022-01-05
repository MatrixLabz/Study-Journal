/* Android with clean and multi module architecture */
package com.matrix.studyNote.ui.note

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.core.domain.models.Session
import com.matrix.core.ui.StateOwner
import com.matrix.core.ui.models.ListState
import com.matrix.core.utils.navigation.models.Properties
import com.matrix.studyNote.domain.usecases.DeleteSessionUseCase
import com.matrix.studyNote.domain.usecases.FetchSessionsUseCase
import com.matrix.studyNote.domain.usecases.RestoreSessionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    fetchSessionsUseCase: FetchSessionsUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase,
    private val restoreSessionUseCase: RestoreSessionUseCase
) : ViewModel(), StateOwner<ListState<Session>> {
    private val noteId = savedStateHandle.get<Properties>("properties")?.id ?: 0

    override val state = fetchSessionsUseCase(noteId)
        .stateIn(viewModelScope, SharingStarted.Lazily, ListState())

    fun deleteSession(noteId: Int, session: Session) {
        viewModelScope.launch { deleteSessionUseCase(noteId, session) }
    }

    fun restoreSession(noteId: Int, session: Session) {
        viewModelScope.launch { restoreSessionUseCase(noteId, session) }
    }
}
