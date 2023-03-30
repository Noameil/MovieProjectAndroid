package hackeru.noameil.finalmoviesproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hackeru.noameil.finalmoviesproject.data.daos.MovieDao
import hackeru.noameil.finalmoviesproject.data.models.Genre
import hackeru.noameil.finalmoviesproject.data.models.Movie


private const val DB_VERSION = 1

@Database(version = DB_VERSION, entities = [Movie::class, Genre::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration()
                .build()
        }
    }
}