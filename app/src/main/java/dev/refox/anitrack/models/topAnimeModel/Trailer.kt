package dev.refox.anitrack.models.topAnimeModel


import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("embed_url")
    val embedUrl: String,
    @SerializedName("images")
    val images: dev.refox.anitrack.models.topAnimeModel.ImagesX,
    @SerializedName("url")
    val url: String,
    @SerializedName("youtube_id")
    val youtubeId: String
)