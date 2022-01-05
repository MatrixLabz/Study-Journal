/* Android with clean and multi module architecture */
@file:Suppress("unused")

package com.matrix.studyjournal.di

import com.matrix.main.ui.MainFragment
import androidx.fragment.app.Fragment
import com.matrix.timer.ui.TimerFragment
import com.matrix.studyNote.ui.details.DetailsFragment
import com.matrix.studyNote.ui.note.NoteFragment
import com.matrix.studyNote.ui.sessionDialog.SessionDialogFragment
import com.matrix.core.di.FragmentKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface InjectFactoryModule {

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    fun bindMainFragment(fragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(DetailsFragment::class)
    fun bindDetailsFragment(fragment: DetailsFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(NoteFragment::class)
    fun bindNoteFragment(fragment: NoteFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SessionDialogFragment::class)
    fun bindSessionDialogFragment(fragment: SessionDialogFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(TimerFragment::class)
    fun bindTimerFragment(fragment: TimerFragment): Fragment
}
