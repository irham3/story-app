package org.irham3.storyapp.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.irham3.storyapp.data.remote.response.*
import org.irham3.storyapp.data.remote.retrofit.ApiService
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val apiService: ApiService
){
    fun getAllStories(token: String) : Flow<Result<List<StoryItem>>> =
        flow {
            emit(Result.Loading())
            val response = apiService.getAllStories(token)

            if(response.isSuccessful)
                emit(Result.Success(response.body()?.listStory))
            else
                emit(Result.Error(response.message().toString()))

        }.flowOn(Dispatchers.IO)

    fun createNewStory(token: String, description: RequestBody, photoFile: MultipartBody.Part) : Flow<Result<StatusResponse>> =
        flow {
            emit(Result.Loading())
            val response = apiService.createNewStory("Bearer $token", description, photoFile)

            if(response.isSuccessful)
                emit(Result.Success(response.body()))
            else
                emit(Result.Error(response.body()?.message))

        }.flowOn(Dispatchers.IO)

    fun getDetailStory(token: String, id: String) : Flow<Result<DetailStoryResponse>> =
        flow {
            emit(Result.Loading())
            val response = apiService.getDetailStory(token, id)

            if(response.isSuccessful)
                emit(Result.Success(response.body()))
            else
                emit(Result.Error(response.body()?.message))

        }.flowOn(Dispatchers.IO)

}