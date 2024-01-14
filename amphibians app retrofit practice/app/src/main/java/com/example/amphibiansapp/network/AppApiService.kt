package com.example.amphibiansapp.network

import com.example.amphibiansapp.data.DataStructure
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET


private const val Base_Url = "https://android-kotlin-fun-mars-server.appspot.com/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(Base_Url)
    .build()

interface AppApiService{

    @GET("amphibians")
     suspend fun getFullData() : List<DataStructure>

}

object AmphibiansAppApi{
    val retrofitService : AppApiService by lazy{
        retrofit.create(AppApiService::class.java)
    }

}