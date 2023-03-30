package hackeru.noameil.finalmoviesproject.data.repository

import hackeru.noameil.finalmoviesproject.data.daos.MovieDao
import hackeru.noameil.finalmoviesproject.service.TMDBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val movieDao: MovieDao) {
    suspend fun refreshMovies() {
        withContext(Dispatchers.IO) {
            val service = TMDBService.create()

            val movieResponse = service.popularMovies()
            val genreResponse = service.genres()

            movieDao.addMovies(movieResponse.movies)
            movieDao.addGenres(genreResponse.genres)
        }
    }
}