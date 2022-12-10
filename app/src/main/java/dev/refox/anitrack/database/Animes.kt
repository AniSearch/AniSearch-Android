package dev.refox.anitrack.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime_list_table")
data class Animes(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var name: String = "",
    var episodes: Int = 0,
    var status: String = "",
    var season: String = "",
    var url: String = "",
    var noOfEpisodes: Int = 0,
)