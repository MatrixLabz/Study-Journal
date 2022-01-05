/* Android with clean and multi module architecture */
package com.matrix.studyNote.domain.storage

import com.matrix.studyNote.domain.storage.models.UserInput

/**
 * Storage for performing read and write operations entered by the user.
 */
interface UserInputStorage {

    /**
     * Saves a user input to the storage.
     */
    fun saveUserInput(userInput: UserInput)

    /**
     * Gets the last saved user input from the storage.
     *
     * @return [UserInput]
     */
    fun getUserInput(): UserInput
}
