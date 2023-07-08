package org.irham3.storyapp.data.remote.retrofit

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.irham3.storyapp.data.remote.response.DetailStoryResponse
import org.irham3.storyapp.data.remote.response.ListStoryResponse
import org.irham3.storyapp.data.remote.response.LoginResponse
import org.irham3.storyapp.data.remote.response.StatusResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("/v1/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<StatusResponse>

    @FormUrlEncoded
    @POST("v1/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<LoginResponse>

    @Multipart
    @POST("v1/stories")
    suspend fun createNewStory(
        @Header("Authorization") token: String,
        @Part("description") description: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("lat") lat: RequestBody? = null,
        @Part("lon") lon: RequestBody? = null,
    ) : Response<StatusResponse>

    @GET("v1/stories")
    suspend fun getAllStories(
        @Header("Authorization") token: String,
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("location") location: Int? = null
    ) : Response<ListStoryResponse>

    @GET("v1/stories/{id}")
    suspend fun getDetailStory(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ) : Response<DetailStoryResponse>

}