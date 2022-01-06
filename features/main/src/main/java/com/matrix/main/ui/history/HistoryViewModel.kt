/* Android with clean and multi module architecture */
package com.matrix.main.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.core.domain.models.StudyNote
import com.matrix.core.ui.StateOwner
import com.matrix.core.ui.models.ListState
import com.matrix.main.domain.AddNewStudyNoteUseCase
import com.matrix.main.domain.DeleteStudyNoteUseCase
import com.matrix.main.domain.FetchStudyNotesUseCase
import com.matrix.main.domain.RestoreStudyNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    fetchStudyNotesUseCase: FetchStudyNotesUseCase,
    private val deleteStudyNoteUseCase: DeleteStudyNoteUseCase,
    private val restoreStudyNoteUseCase: RestoreStudyNoteUseCase,
    private val addNewStudyNoteUseCase: AddNewStudyNoteUseCase
) : ViewModel(), StateOwner<ListState<StudyNote>> {

    override val state = fetchStudyNotesUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, ListState())

    fun deleteWorkoutNote(studyNote: StudyNote) {
        viewModelScope.launch { deleteStudyNoteUseCase(studyNote) }
    }

    fun restoreWorkoutNote(studyNote: StudyNote) {
        viewModelScope.launch { restoreStudyNoteUseCase(studyNote) }
    }

    suspend fun addNewWorkoutNote() =
        withContext(viewModelScope.coroutineContext) { addNewStudyNoteUseCase() }
}
