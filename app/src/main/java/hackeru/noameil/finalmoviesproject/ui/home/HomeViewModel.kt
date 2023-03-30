package hackeru.noameil.finalmoviesproject.ui.home

import android.app.Application
import androidx.lifecycle.*
import hackeru.noameil.finalmoviesproject.AppDatabase
import hackeru.noameil.finalmoviesproject.TMDBApp
import hackeru.noameil.finalmoviesproject.data.models.Movie
import hackeru.noameil.finalmoviesproject.data.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {

    val movies: LiveData<List<Movie>> = TMDBApp.db.movieDao().getMovies()

    init {
        viewModelScope.launch {
            TMDBApp.movieRepository.refreshMovies()
        }
    }
}