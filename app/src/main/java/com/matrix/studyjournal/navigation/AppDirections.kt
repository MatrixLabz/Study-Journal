
package com.matrix.studyjournal.navigation

import androidx.navigation.NavDirections
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.core.utils.navigation.models.Properties
import com.matrix.main.ui.MainFragmentDirections
import com.matrix.main.ui.splashscreen.SplashFragmentDirections
import com.matrix.timer.ui.TimerFragmentDirections
import com.matrix.studyNote.ui.sessionDialog.SessionDialogFragmentDirections
import javax.inject.Inject

/**
 * Default implementation of [GlobalDirections] interface.
 */
class AppDirections @Inject constructor() : GlobalDirections {

    override fun fromSplashToMain() =
        SplashFragmentDirections.actionSplashFragmentToMainFragment()

    override fun fromSessionDialogToTimer(properties: Properties) =
        SessionDialogFragmentDirections.actionSessionFragmentToTimerFragment(properties)

    override fun fromMainToStudyNote(properties: Properties) =
        MainFragmentDirections.actionMainFragmentToStudyNoteGraph(properties)

    override fun fromTimerToStudyNote(properties: Properties) =
        TimerFragmentDirections.actionTimerFragmentToNoteFragment(properties)
}
