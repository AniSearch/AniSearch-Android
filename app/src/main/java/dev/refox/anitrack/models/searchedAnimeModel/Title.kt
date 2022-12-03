package dev.refox.anitrack.models.searchedAnimeModel


import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String
)