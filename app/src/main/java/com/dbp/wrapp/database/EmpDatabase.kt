package com.dbp.wrapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dbp.wrapp.network.responseModels.EmpResponse


@Database(entities = [EmpResponse::class], version = 1)
abstract class EmpDatabase : RoomDatabase() {

    abstract fun getUserDao(): EmpDao

    /**
     * Singleton Instance for database
     */
    companion object{
        @Volatile
        private var instance: EmpDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EmpDatabase::class.java,
                "MyDatabase.db"
            ).build()
    }
}