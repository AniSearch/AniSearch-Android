package dev.refox.anitrack.networking

import dev.refox.anitrack.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AnimeRetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : AnimeApiInterface by lazy {
        retrofit.create(AnimeApiInterface::class.java)
    }
}