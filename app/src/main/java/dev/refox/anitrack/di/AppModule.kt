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
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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