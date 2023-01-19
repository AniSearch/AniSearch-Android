package dev.refox.anitrack.networking


import dev.refox.anitrack.models.topAnimeModel.TopAnime
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val animeApiInterface: AnimeApiInterface) {
    suspend fun getTopAnime(): Response<TopAnime>{
        return animeApiInterface.getTopAnime()
    }
    suspend fun getAnimeSearch(queryString: String): Response<TopAnime> {
        return animeApiInterface.getAnimeSearch(queryString);
    }
}