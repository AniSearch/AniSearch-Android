package dev.refox.anitrack.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Animes::class], version = 1)
abstract class AnimesRoomDatabase : RoomDatabase() {

    abstract fun animesDao(): AnimesDao

    companion object {
        @Volatile
        private var INSTANCE: AnimesRoomDatabase? = null

        fun getAnimesDatabase(context: Context): AnimesRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimesRoomDatabase::class.java,
                    "Animes_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}