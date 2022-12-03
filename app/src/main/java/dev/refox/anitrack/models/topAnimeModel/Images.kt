package dev.refox.anitrack.models.topAnimeModel


import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("jpg")
    val jpg: dev.refox.anitrack.models.topAnimeModel.Jpg,
    @SerializedName("webp")
    val webp: dev.refox.anitrack.models.topAnimeModel.Webp
)