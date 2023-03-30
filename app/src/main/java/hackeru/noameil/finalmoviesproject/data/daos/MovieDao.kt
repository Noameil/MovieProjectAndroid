package hackeru.noameil.finalmoviesproject.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hackeru.noameil.finalmoviesproject.data.models.Genre
import hackeru.noameil.finalmoviesproject.data.models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movie: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGenres(genres: List<Genre>)

    @Query("SELECT * FROM Movie")
    fun getMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM Genre")
    fun getGenres(): LiveData<List<Genre>>
}