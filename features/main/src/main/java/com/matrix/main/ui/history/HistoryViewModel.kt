/* Android with clean and multi module architecture */
package com.matrix.main.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.core.domain.models.StudyNote
import com.matrix.core.ui.StateOwner
import com.matrix.core.ui.models.ListState
import com.matrix.main.domain.AddNewWorkoutNoteUseCase
import com.matrix.main.domain.DeleteWorkoutNoteUseCase
import com.matrix.main.domain.FetchWorkoutNotesUseCase
import com.matrix.main.domain.RestoreWorkoutNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    fetchWorkoutNotesUseCase: FetchWorkoutNotesUseCase,
    private val deleteWorkoutNoteUseCase: DeleteWorkoutNoteUseCase,
    private val restoreWorkoutNoteUseCase: RestoreWorkoutNoteUseCase,
    private val addNewWorkoutNoteUseCase: AddNewWorkoutNoteUseCase
) : ViewModel(), StateOwner<ListState<StudyNote>> {

    override val state = fetchWorkoutNotesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, ListState())

    fun deleteWorkoutNote(studyNote: StudyNote) {
        viewModelScope.launch { deleteWorkoutNoteUseCase(studyNote) }
    }

    fun restoreWorkoutNote(studyNote: StudyNote) {
        viewModelScope.launch { restoreWorkoutNoteUseCase(studyNote) }
    }

    suspend fun addNewWorkoutNote() =
        withContext(viewModelScope.coroutineContext) { addNewWorkoutNoteUseCase() }
}
