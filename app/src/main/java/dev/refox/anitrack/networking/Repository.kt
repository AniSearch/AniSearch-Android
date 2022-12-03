package dev.refox.anitrack.networking

import dev.refox.anitrack.models.searchedAnimeModel.SearchedAnime
import dev.refox.anitrack.models.topAnimeModel.TopAnime
import retrofit2.Call

class Repository {
    suspend fun getTopAnime(): Call<dev.refox.anitrack.models.topAnimeModel.TopAnime>{
        return AnimeRetrofitInstance.api.getTopAnime()
    }
    suspend fun getAnimeSearch(queryString: String): Call<SearchedAnime> {
        return AnimeRetrofitInstance.api.getAnimeSearch(queryString);
    }
}