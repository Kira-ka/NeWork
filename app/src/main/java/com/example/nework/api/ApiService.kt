package com.example.nework.api

import com.example.nework.BuildConfig
import com.example.nework.dto.Event
import com.example.nework.dto.Job
import com.example.nework.dto.Media
import com.example.nework.dto.Post
import com.example.nework.dto.Token
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

private const val BASE_URL = "${BuildConfig.BASE_URL}/api/"

fun okhttpMedia(): OkHttpClient = OkHttpClient.Builder()
    .build()

fun okhttp(vararg interceptors: Interceptor): OkHttpClient = OkHttpClient.Builder()
    .apply {
        interceptors.forEach {
            this.addInterceptor(it)
        }
    }
    .build()

fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(client)
    .build()


interface ApiService {

    @GET("events")
    suspend fun getAllEvents(): Response<List<Event>>

    @POST("events")
    suspend fun saveEvent(@Body event: Event): Response<Event>

    @DELETE("events/{id}")
    suspend fun deleteEvent(@Path("id") id: Long): Response<Event>

    @POST("events/{id}/likes")
    suspend fun likeByIdEvent(@Path("id") id: Long): Response<Event>

    @DELETE("events/{id}/likes")
    suspend fun dislikeByIdEvent(@Path("id") id: Long): Response<Event>



    @GET("posts")
    suspend fun getAllPosts(): Response<List<Post>>

    @POST("posts")
    suspend fun savePost(@Body post: Post): Response<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Long): Response<Post>

    @POST("posts/{id}/likes")
    suspend fun likeByIdPost(@Path("id") id: Long): Response<Post>

    @DELETE("posts/{id}/likes")
    suspend fun dislikeByIdPost(@Path("id") id: Long): Response<Post>


    @GET("my/jobs")
    suspend fun getAllMyJobs(): Response<List<Job>>

    @POST("my/jobs")
    suspend fun saveJob(@Body job: Job): Response<Job>

    @DELETE("my/jobs/{id}")
    suspend fun deleteJob(@Path("id") id: Long): Response<Job>

    @FormUrlEncoded
    @POST("users/authentication")
    suspend fun updateUser(@Field("login") login: String, @Field("pass") pass: String): Response<Token>

    @FormUrlEncoded
    @POST("users/registration")
    suspend fun registerUser(@Field("login") login: String, @Field("pass") pass: String, @Field("name") name: String): Response<Token>
}

interface MediaService {

    @Multipart
    @POST("media")
    suspend fun upload(@Part media: MultipartBody.Part): Response<Media>
}
