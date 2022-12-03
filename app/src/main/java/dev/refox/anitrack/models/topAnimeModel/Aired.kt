package dev.refox.anitrack.models.topAnimeModel


import com.google.gson.annotations.SerializedName

data class Aired(
    @SerializedName("from")
    val from: String,
    @SerializedName("prop")
    val prop: dev.refox.anitrack.models.topAnimeModel.Prop,
    @SerializedName("string")
    val string: String,
    @SerializedName("to")
    val to: String
)