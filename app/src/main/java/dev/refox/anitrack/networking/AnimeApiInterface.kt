package dev.refox.anitrack.networking

import dev.refox.anitrack.searchedAnimeModel.SearchedAnime
import dev.refox.anitrack.topAnimeModel.TopAnime
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApiInterface {

    @GET("top/anime")
    suspend fun getTopAnime(): Call<TopAnime>

    @GET("/anime")
    suspend fun getAnimeSearch(@Query("q")queryString: String): Call<SearchedAnime>
}