/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.usecases

import com.matrix.studyNote.domain.storage.UserInputStorage
import javax.inject.Inject

/**
 * Gets a user input from the storage.
 */
class GetUserInputUseCase @Inject constructor(private val userInputStorage: UserInputStorage) {
    operator fun invoke() = userInputStorage.getUserInput()
}
