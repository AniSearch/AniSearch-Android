package dev.refox.anitrack.database

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.refox.anitrack.networking.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimesDBViewModel @Inject constructor(private val repository: AnimesRepository) : ViewModel() {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)


    val allAnimesLists: LiveData<MutableList<Animes>> = repository.allAnimesLists

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

class AnimesDBViewModelFactory(val repository: AnimesRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return AnimesDBViewModel(repository) as T
    }
}