

package com.matrix.studyNote.data

import android.content.SharedPreferences
import com.matrix.core.di.UserInputSharedPreferences
import com.matrix.studyNote.domain.storage.UserInputStorage
import com.matrix.studyNote.domain.storage.models.UserInput
import javax.inject.Inject

private const val NAME = "name"
private const val NAME_DEF_VALUE = ""
private const val REPETITIONS = "repetitions"
private const val REPETITIONS_DEF_VALUE = ""
private const val BREAK_DURATION = "break-duration"
private const val BREAK_DURATION_DEF_VALUE = "15"

/**
 * Shared Preferences implementation of [UserInputStorage] interface using [SharedPreferences].
 */
class SharedPrefUserInputStorage @Inject constructor(
    @UserInputSharedPreferences private val sharedPreferences: SharedPreferences
) : UserInputStorage {

    override fun saveUserInput(userInput: UserInput) {
        sharedPreferences.edit()
            .putString(NAME, userInput.name)
            .putString(REPETITIONS, userInput.repetitions)
            .putString(BREAK_DURATION, userInput.breakDuration)
            .apply()
    }

    override fun getUserInput() = UserInput(
        name = sharedPreferences.getString(NAME, NAME_DEF_VALUE) ?: "",
        repetitions = sharedPreferences.getString(REPETITIONS, REPETITIONS_DEF_VALUE) ?: "",
        breakDuration = sharedPreferences.getString(BREAK_DURATION, BREAK_DURATION_DEF_VALUE) ?: ""
    )
}
