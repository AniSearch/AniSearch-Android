package dev.refox.anitrack.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AnimesDao {
    @Query("SELECT * FROM anime_list_table")
    fun getAllAnimes(): LiveData<MutableList<Animes>>

    @Insert
    fun insertAnimes(vararg attendance: Animes)

    @Delete
    fun deleteAnimes(vararg attendance: Animes)

    @Update
    fun updateAnimes(vararg attendance: Animes)
}