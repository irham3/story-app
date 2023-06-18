package org.irham3.storyapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.irham3.storyapp.data.local.AuthPreferences
import org.irham3.storyapp.data.local.dataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    fun provideDataStore(@ApplicationContext context: Context) : DataStore<Preferences> =
        context.dataStore

    @Singleton
    @Provides
    fun provideAuthPreferences(dataStore: DataStore<Preferences>) : AuthPreferences =
        AuthPreferences(dataStore)
}