package com.example.hw_14.Model

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object RetrofitObject {

    private const val BASE_URL = "\"https://run.mocky.io/"
    val retrofitBuilder by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create()).build()
    }

    fun getProducts()= retrofitBuilder.create(InfoGetter::class.java)

}

interface InfoGetter{

    @GET("v3/f4864c66-ee04-4e7f-88a2-2fbd912ca5ab")
    suspend fun infoGet(): Response<LikusData>
}