package com.privotech.kotlinapp.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataClass::class], version = 1)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun setDao(): SetDAO?

    companion object {
        private var INSTANCE: ApplicationDatabase? = null
        fun getDatabase(context: Context): ApplicationDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java, "Database"
                ).build()
            }
            return INSTANCE
        }
    }
}