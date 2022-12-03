package dev.refox.anitrack.networking

import dev.refox.anitrack.models.searchedAnimeModel.SearchedAnime
import dev.refox.anitrack.models.topAnimeModel.TopAnime
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AnimeApiInterface {

    @Headers("Content-Type: application/json")

    @GET("top/anime/")
    suspend fun getTopAnime(): Response<TopAnime>

    @GET("/anime")
    suspend fun getAnimeSearch(@Query("q")queryString: String): Response<SearchedAnime>
}