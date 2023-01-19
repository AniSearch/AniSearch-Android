package dev.refox.anitrack.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.refox.anitrack.database.AnimesDao
import dev.refox.anitrack.database.AnimesRepository
import dev.refox.anitrack.database.AnimesRoomDatabase
import dev.refox.anitrack.networking.AnimeApiInterface
import dev.refox.anitrack.networking.AnimeRetrofitInstance
import dev.refox.anitrack.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    private var mClient: OkHttpClient? = null

    val client: OkHttpClient
        get() {
            if (mClient == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BASIC

                val httpBuilder = OkHttpClient.Builder()
                httpBuilder
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)  /// show all JSON in logCat
                mClient = httpBuilder.build()

            }
            return mClient!!
        }

    @Provides
    fun getRetrofitService(retrofit: Retrofit): AnimeApiInterface {
        return retrofit.create(AnimeApiInterface::class.java)
    }
    @Provides
    fun getRetofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun provideAnimeRepository(dao: AnimesDao): AnimesRepository {
        return AnimesRepository(dao)
    }

    @Provides
    fun provideAnimesDao(db: AnimesRoomDatabase) = db.animesDao()

    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AnimesRoomDatabase::class.java, "Animes_Database").build()



}