/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.usecases

import com.matrix.core.domain.CrudRepository
import com.matrix.studyNote.domain.storage.UserInputStorage
import com.matrix.studyNote.domain.storage.models.UserInput
import com.matrix.studyNote.domain.storage.models.toSession
import javax.inject.Inject

/**
 * Saves a session to the database.
 */
class SaveSessionUseCase @Inject constructor(
    private val repository: CrudRepository,
    private val userInputStorage: UserInputStorage
) {

    suspend operator fun invoke(noteId: Int, userInput: UserInput) {
        repository.saveSession(noteId, userInput.toSession())
        userInputStorage.saveUserInput(userInput)
    }
}
