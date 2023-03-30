package hackeru.noameil.finalmoviesproject.ui.home

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hackeru.noameil.finalmoviesproject.TMDBApp
import hackeru.noameil.finalmoviesproject.data.models.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(application: Application): AndroidViewModel(application) {

    val movies: LiveData<List<Movie>> = TMDBApp.db.movieDao().getMovies()

    init {
        viewModelScope.launch {
            TMDBApp.movieRepository.refreshMovies()
        }
    }
}