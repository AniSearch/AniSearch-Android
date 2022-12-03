package dev.refox.anitrack.networking

import dev.refox.anitrack.models.searchedAnimeModel.SearchedAnime
import dev.refox.anitrack.models.topAnimeModel.TopAnime
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApiInterface {

    @GET("top/anime")
    suspend fun getTopAnime(): Call<dev.refox.anitrack.models.topAnimeModel.TopAnime>

    @GET("/anime")
    suspend fun getAnimeSearch(@Query("q")queryString: String): Call<SearchedAnime>
}