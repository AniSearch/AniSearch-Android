package dev.refox.anitrack.networking

import dev.refox.anitrack.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object AnimeRetrofitInstance {

    private var mClient: OkHttpClient? = null

    val client: OkHttpClient
    get() {
        if (mClient == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC

            val httpBuilder = OkHttpClient.Builder()
            httpBuilder
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)  /// show all JSON in logCat
            mClient = httpBuilder.build()

        }
        return mClient!!
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    val api : AnimeApiInterface by lazy {
        retrofit.create(AnimeApiInterface::class.java)
    }
}