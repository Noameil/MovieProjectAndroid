package hackeru.noameil.finalmoviesproject.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import hackeru.noameil.finalmoviesproject.BuildConfig
import hackeru.noameil.finalmoviesproject.data.AppDatabase
import hackeru.noameil.finalmoviesproject.service.TMDBService
import hackeru.noameil.finalmoviesproject.utils.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkingModule {
    private val tmdbApiKey = BuildConfig.TMDB_API_KEY
    private val tmdbBaseURL = BuildConfig.TMDB_BASE_URL
    private const val DB_NAME = "MovieDatabase"

    @Provides
    fun provideTokenInterceptor(): TokenInterceptor {
        return TokenInterceptor(queryValue = tmdbApiKey)
    }

    @Provides
    fun provideTmdbService(tokenInterceptor: TokenInterceptor): TMDBService {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(tmdbBaseURL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDBService::class.java)
    }

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }
}