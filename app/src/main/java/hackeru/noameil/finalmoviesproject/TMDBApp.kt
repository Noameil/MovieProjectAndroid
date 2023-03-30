package hackeru.noameil.finalmoviesproject

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import hackeru.noameil.finalmoviesproject.data.AppDatabase
import hackeru.noameil.finalmoviesproject.data.repository.MovieRepository

@HiltAndroidApp
class TMDBApp: Application() {
    override fun onCreate() {
        super.onCreate()
        app = this
        db = AppDatabase.create(this)
        movieRepository = MovieRepository(db.movieDao())
    }

    companion object {
        lateinit var app: TMDBApp
        lateinit var db: AppDatabase
        lateinit var movieRepository: MovieRepository
    }
}