/* Android with clean and multi module architecture */
package com.matrix.studyNote.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrix.studyNote.domain.usecases.RenameWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val renameWorkoutUseCase: RenameWorkoutUseCase
) : ViewModel() {

    fun renameWorkout(noteId: Int, title: String) {
        viewModelScope.launch { renameWorkoutUseCase(noteId, title) }
    }
}
