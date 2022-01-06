/* Android with clean and multi module architecture */
package com.matrix.core.utils.navigation

import androidx.navigation.NavDirections
import com.matrix.core.utils.navigation.models.Properties

/**
 * Interface to perform intermodular navigation.
 */
interface GlobalDirections {
    fun fromSplashToMain(): NavDirections
    fun fromSessionDialogToTimer(properties: Properties): NavDirections
    fun fromMainToStudyNote(properties: Properties): NavDirections
    fun fromTimerToStudyNote(properties: Properties): NavDirections
}
