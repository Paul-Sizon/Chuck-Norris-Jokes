package com.example.chucknorris.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.icndb.com/"



private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


interface SimpleApi {


    @GET("jokes/random/3/firstName=Chuck&lastName=Norris")
    suspend fun getPost(): Post

    @GET("jokes/random/{postNum}/firstName=Chuck&lastName=Norris")
    suspend fun getPost2(
        @Path("postNum") number:Int
    ): Post


}


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()



object retroApi {
    val api : SimpleApi by lazy {
        retrofit.create(SimpleApi::class.java)
    }
}