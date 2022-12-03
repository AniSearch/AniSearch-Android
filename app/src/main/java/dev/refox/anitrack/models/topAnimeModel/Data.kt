package dev.refox.anitrack.models.topAnimeModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("aired")
    val aired: dev.refox.anitrack.models.topAnimeModel.Aired,
    @SerializedName("airing")
    val airing: Boolean,
    @SerializedName("approved")
    val approved: Boolean,
    @SerializedName("background")
    val background: String,
    @SerializedName("broadcast")
    val broadcast: dev.refox.anitrack.models.topAnimeModel.Broadcast,
    @SerializedName("demographics")
    val demographics: List<dev.refox.anitrack.models.topAnimeModel.Demographic>,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("episodes")
    val episodes: Int,
    @SerializedName("explicit_genres")
    val explicitGenres: List<Any>,
    @SerializedName("favorites")
    val favorites: Int,
    @SerializedName("genres")
    val genres: List<dev.refox.anitrack.models.topAnimeModel.Genre>,
    @SerializedName("images")
    val images: dev.refox.anitrack.models.topAnimeModel.Images,
    @SerializedName("licensors")
    val licensors: List<dev.refox.anitrack.models.topAnimeModel.Licensor>,
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("members")
    val members: Int,
    @SerializedName("popularity")
    val popularity: Int,
    @SerializedName("producers")
    val producers: List<dev.refox.anitrack.models.topAnimeModel.Producer>,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("score")
    val score: Double,
    @SerializedName("scored_by")
    val scoredBy: Int,
    @SerializedName("season")
    val season: String,
    @SerializedName("source")
    val source: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("studios")
    val studios: List<dev.refox.anitrack.models.topAnimeModel.Studio>,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("themes")
    val themes: List<dev.refox.anitrack.models.topAnimeModel.Theme>,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_english")
    val titleEnglish: String,
    @SerializedName("title_japanese")
    val titleJapanese: String,
    @SerializedName("title_synonyms")
    val titleSynonyms: List<String>,
    @SerializedName("titles")
    val titles: List<dev.refox.anitrack.models.topAnimeModel.Title>,
    @SerializedName("trailer")
    val trailer: dev.refox.anitrack.models.topAnimeModel.Trailer,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("year")
    val year: Int
)