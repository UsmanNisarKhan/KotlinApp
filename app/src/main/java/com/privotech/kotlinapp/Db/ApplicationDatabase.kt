package com.privotech.kotlinapp.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataClass::class,CarClass::class], version = 3)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun setDao(): SetDAO?
    abstract fun carDao(): carDao?

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