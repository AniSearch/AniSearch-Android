package dev.refox.anitrack.viewmodels

import androidx.lifecycle.*
import dev.refox.anitrack.models.topAnimeModel.TopAnime
import dev.refox.anitrack.networking.AnimeRetrofitInstance
import dev.refox.anitrack.networking.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class AnimeViewModel(private val repository: Repository): ViewModel() {

    private val _searchResponse = MutableLiveData<Response<TopAnime>>()
    val searchResponse: LiveData<Response<TopAnime>> get() = _searchResponse

    private val _topResponse = MutableLiveData<Response<TopAnime>>()
    val topResponse: LiveData<Response<TopAnime>> get() = _topResponse

    fun getTopAnime() {
        viewModelScope.launch {
            val tResponse = repository.getTopAnime()
            _topResponse.value = tResponse
        }
    }

    fun getAnimeSearch(queryString: String){
        viewModelScope.launch {
            val sResponse = repository.getAnimeSearch(queryString)
            _searchResponse.value = sResponse
        }
    }
}

class AnimeViewModelFactory(val repository: Repository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return AnimeViewModel(repository) as T
    }
}