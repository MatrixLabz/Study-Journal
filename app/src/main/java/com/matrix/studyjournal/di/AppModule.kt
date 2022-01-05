/* Android with clean and multi module architecture */

package com.matrix.studyjournal.di

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.matrix.core.data.db.AppDatabase
import com.matrix.core.di.DefaultSharedPreferences
import com.matrix.core.di.UserInputSharedPreferences
import com.matrix.core.utils.NotificationChannel.NOTIFICATION_CHANNEL_ID
import com.matrix.core.utils.navigation.GlobalNavHost
import com.matrix.studyjournal.BuildConfig
import com.matrix.studyjournal.HostActivity
import com.matrix.studyjournal.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRoomDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "workout_notes")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    @UserInputSharedPreferences
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("user-input-preferences", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    @DefaultSharedPreferences
    fun provideDefaultSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideGlobalNavHost() = GlobalNavHost(R.id.navHostFragment)

    @Provides
    @Singleton
    fun provideVersionName() = BuildConfig.VERSION_NAME

    @Provides
    @Singleton
    fun providePendingIntent(@ApplicationContext context: Context): PendingIntent =
        PendingIntent.getActivity(
            context,
            0,
            Intent(context, HostActivity::class.java).also {
                it.action = Intent.ACTION_MAIN
                it.addCategory(Intent.CATEGORY_LAUNCHER)
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

    @Provides
    @Singleton
    fun provideNotification(
        @ApplicationContext context: Context,
        pendingIntent: PendingIntent
    ): Notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setAutoCancel(false)
        .setOngoing(true)
        .setContentTitle(context.getString(R.string.notification_content_title))
        .setContentText(context.getString(R.string.notification_content_text))
        .setSmallIcon(R.drawable.ic_timer)
        .setContentIntent(pendingIntent)
        .build()
}
