package com.example.apinews.service

import com.example.apinews.model.ResponseNews
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

object RetrofitBuilder{
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    fun getService() = retrofit.create(TopHeadlines::class.java)
}
interface TopHeadlines{
    @Headers("Authorization: 1f4c1695288b460fa69c95f86b575e51")
    @GET("/v2/top-headlines?country=id")
    fun fetchHeadlines(): Call<ResponseNews>
}