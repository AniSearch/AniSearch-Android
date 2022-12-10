package dev.refox.anitrack.networking


import dev.refox.anitrack.models.topAnimeModel.TopAnime
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimeApiInterface {

    @GET("v4/top/anime/")
    suspend fun getTopAnime(): Response<TopAnime>

    @GET("v4/anime/")
    suspend fun getAnimeSearch(@Query("q")queryString: String): Response<TopAnime>
}