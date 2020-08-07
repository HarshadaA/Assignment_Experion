package com.experion.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.experion.assignment.dao.CaDao
import com.experion.assignment.models.ResponseModel
import com.experion.assignment.utils.Converters

@Database(entities = [ResponseModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CaRoomDatabase : RoomDatabase() {

    abstract fun newsDao(): CaDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CaRoomDatabase? = null

        fun getDatabase(context: Context): CaRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CaRoomDatabase::class.java,
                    "news_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}