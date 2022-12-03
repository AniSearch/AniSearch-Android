package dev.refox.anitrack.models.topAnimeModel


import com.google.gson.annotations.SerializedName

data class TopAnime(
    @SerializedName("data")
    val `data`: List<dev.refox.anitrack.models.topAnimeModel.Data>,
    @SerializedName("pagination")
    val pagination: dev.refox.anitrack.models.topAnimeModel.Pagination
)