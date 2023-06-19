package org.irham3.storyapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.irham3.storyapp.data.local.AuthPreferences
import org.irham3.storyapp.data.remote.response.LoginResponse
import org.irham3.storyapp.data.remote.response.StatusResponse
import org.irham3.storyapp.data.remote.retrofit.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val authPreferences: AuthPreferences
) {
    fun registerUser(name: String, email: String, password: String) : Flow<Result<StatusResponse>> =
        flow {
            emit(Result.Loading())
            val response = apiService.register(name, email, password)

            if(response.isSuccessful)
                emit(Result.Success(response.body()))
            else
                emit(Result.Error(response.message()))

        }.flowOn(Dispatchers.IO)

    fun loginUser(email: String, password: String) : Flow<Result<LoginResponse>> =
        flow {
            emit(Result.Loading())
            val response = apiService.login(email, password)
            if (response.isSuccessful)
                emit(Result.Success(response.body()))
            else{
                emit(Result.Error(response.message()))
            }

        }.flowOn(Dispatchers.IO)

    suspend fun logout() {
        authPreferences.clearPreferences()
    }

    fun getAuthToken(): Flow<String> =
        authPreferences.getAuthToken()

    suspend fun saveAuthToken(token: String) =
        authPreferences.saveAuthToken(token)

}