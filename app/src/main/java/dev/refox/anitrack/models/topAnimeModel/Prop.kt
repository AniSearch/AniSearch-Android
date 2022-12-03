package dev.refox.anitrack.models.topAnimeModel


import com.google.gson.annotations.SerializedName

data class Prop(
    @SerializedName("from")
    val from: dev.refox.anitrack.models.topAnimeModel.From,
    @SerializedName("to")
    val to: dev.refox.anitrack.models.topAnimeModel.To
)