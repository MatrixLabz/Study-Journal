/* Android with clean and multi module architecture */
@file:Suppress("unused")

package com.matrix.studyjournal.di

import com.matrix.core.data.repositories.DefaultCrudRepository
import com.matrix.core.domain.CrudRepository
import com.matrix.core.utils.navigation.GlobalDirections
import com.matrix.timer.data.repositories.DefaultTimerRepository
import com.matrix.timer.domain.repositories.TimerRepository
import com.matrix.studyNote.data.SharedPrefUserInputStorage
import com.matrix.studyNote.domain.storage.UserInputStorage
import com.matrix.studyjournal.navigation.AppDirections
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {
    @Binds
    @Singleton
    fun bindGlobalDirections(appDirections: AppDirections): GlobalDirections

    @Binds
    @Singleton
    fun bindCrudRepository(repository: DefaultCrudRepository): CrudRepository

    @Binds
    fun bindTimerRepository(repository: DefaultTimerRepository): TimerRepository

    @Binds
    @Singleton
    fun bindUserInputStorage(userInputStorage: SharedPrefUserInputStorage): UserInputStorage
}
