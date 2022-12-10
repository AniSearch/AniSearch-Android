package dev.refox.anitrack.networking


import dev.refox.anitrack.models.topAnimeModel.TopAnime
import retrofit2.Call
import retrofit2.Response

class Repository {
    suspend fun getTopAnime(): Response<TopAnime>{
        return AnimeRetrofitInstance.api.getTopAnime()
    }
    suspend fun getAnimeSearch(queryString: String): Response<TopAnime> {
        return AnimeRetrofitInstance.api.getAnimeSearch(queryString);
    }
}