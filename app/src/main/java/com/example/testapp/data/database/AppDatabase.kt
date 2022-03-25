package com.example.testapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapp.data.converter.CharacterConverter

@Database(entities = [CharacterDbModel::class], version = 1, exportSchema = false)
@TypeConverters(CharacterConverter::class)
abstract class AppDatabase: RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DB_NAME = "main.db"
        private var db: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
    abstract fun characterDao(): CharacterDao
}