package dev.refox.anitrack.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnimesDBViewModel(application: Application) : AndroidViewModel(Application()) {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)

    private val repository: AnimesRepository
    val allAnimesLists: LiveData<List<Animes>>

    init {
        val animesDao = AnimesRoomDatabase.getAnimesDatabase(application).animesDao()
        repository = AnimesRepository(animesDao)
        allAnimesLists = repository.allAnimesLists
    }

    fun insertAnimes(animes: Animes) = scope.launch(Dispatchers.IO) {
        repository.insertAnimes(animes)
    }

    fun deleteAnimes(animes: Animes) = scope.launch(Dispatchers.IO) {
        repository.deleteAnimes(animes)
    }

    fun updateAnimes(animes: Animes) = scope.launch(Dispatchers.IO) {
        repository.updateAnimes(animes)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}