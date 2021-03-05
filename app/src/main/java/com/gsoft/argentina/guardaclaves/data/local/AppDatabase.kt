package com.gsoft.argentina.guardaclaves.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gsoft.argentina.guardaclaves.data.model.Entidad

@Database(entities = [Entidad::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao() : DataDAO

    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "guardaclaves"
            ).build()

            return INSTANCE!!
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}