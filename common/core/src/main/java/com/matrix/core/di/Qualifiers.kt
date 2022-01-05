/* Android with clean and multi module architecture */
package com.matrix.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserInputSharedPreferences

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultSharedPreferences
