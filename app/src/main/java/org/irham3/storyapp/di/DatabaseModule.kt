package org.irham3.storyapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.irham3.storyapp.data.local.room.RemoteKeysDao
import org.irham3.storyapp.data.local.room.StoryDao
import org.irham3.storyapp.data.local.room.StoryDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : StoryDatabase =
        Room.databaseBuilder(
            context,
            StoryDatabase::class.java, "StoryDatabase.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideStoryDao(database: StoryDatabase) : StoryDao =
        database.getStoryDao()

    @Provides
    fun provideRemoteKeysDao(database: StoryDatabase) : RemoteKeysDao =
        database.getRemoteKeysDao()
}