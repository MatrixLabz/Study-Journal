/* Android with clean and multi module architecture */
package com.matrix.main.domain

import com.matrix.core.domain.CrudRepository
import com.matrix.core.domain.models.FetchResult
import com.matrix.core.ui.models.ListState
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Fetches study notes from the database.
 */
class FetchStudyNotesUseCase @Inject constructor(
    private val repository: CrudRepository
) {
    operator fun invoke() = repository.fetchStudyNotes().map {
        when (it) {
            is FetchResult.Loading -> ListState()
            is FetchResult.Empty -> ListState(isLoading = false, isEmpty = true)
            is FetchResult.Result ->
                ListState(isLoading = false, isEmpty = false, items = it.value)
        }
    }
}
