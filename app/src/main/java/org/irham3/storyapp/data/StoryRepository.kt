package org.irham3.storyapp.data

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.irham3.storyapp.data.local.entity.StoryEntity
import org.irham3.storyapp.data.local.room.StoryDatabase
import org.irham3.storyapp.data.remote.response.*
import org.irham3.storyapp.data.remote.retrofit.ApiService
import javax.inject.Inject

class StoryRepository @Inject constructor(
    private val db: StoryDatabase,
    private val apiService: ApiService
){
//    fun getAllStories(token: String) : Flow<Result<List<StoryItem>>> =
//        flow {
//            emit(Result.Loading())
//            val response = apiService.getAllStories(token)
//
//            if(response.isSuccessful)
//                emit(Result.Success(response.body()?.listStory))
//            else
//                emit(Result.Error(response.message().toString()))
//
//        }.flowOn(Dispatchers.IO)


    fun getAllStories(token: String) : LiveData<PagingData<StoryEntity>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoryRemoteMediator(db, apiService, token),
            pagingSourceFactory = {
                db.getStoryDao().getAllStory()
            }
        ).liveData
    }

    fun getStoriesWithLocation(token: String) : Flow<Result<List<StoryResponse>>> =
        flow {
            emit(Result.Loading())
            val response = apiService.getAllStories(token = "Bearer $token", location = 1)

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

    suspend fun getDetailStory(id: String) : StoryEntity =
        db.getStoryDao().getStoryWithId(id)

}