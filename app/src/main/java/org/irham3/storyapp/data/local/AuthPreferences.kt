package org.irham3.storyapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")
class AuthPreferences @Inject constructor(private val dataStore: DataStore<Preferences>){

    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
    }

    fun getAuthToken() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    suspend fun saveAuthToken(token: String) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[TOKEN_KEY] = token
        }
    }

    suspend fun clearPreferences() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}