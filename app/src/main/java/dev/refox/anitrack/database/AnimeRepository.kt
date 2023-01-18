package dev.refox.anitrack.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import javax.inject.Inject

class AnimesRepository @Inject constructor(private val animesDao: AnimesDao) {

    val allAnimesLists: LiveData<MutableList<Animes>> = animesDao.getAllAnimes()

    @WorkerThread
    fun insertAnimes(animes: Animes) {
        animesDao.insertAnimes(animes)
    }

    @WorkerThread
    fun deleteAnimes(animes: Animes) {
        animesDao.deleteAnimes(animes)
    }

    @WorkerThread
    fun updateAnimes(animes: Animes) {
        animesDao.updateAnimes(animes)
    }
}