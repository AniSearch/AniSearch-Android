package dev.refox.anitrack.networking

import dev.refox.anitrack.searchedAnimeModel.SearchedAnime
import dev.refox.anitrack.topAnimeModel.TopAnime
import retrofit2.Call

class Repository {
    suspend fun getTopAnime(): Call<TopAnime>{
        return AnimeRetrofitInstance.api.getTopAnime()
    }
    suspend fun getAnimeSearch(queryString: String): Call<SearchedAnime> {
        return AnimeRetrofitInstance.api.getAnimeSearch(queryString);
    }
}