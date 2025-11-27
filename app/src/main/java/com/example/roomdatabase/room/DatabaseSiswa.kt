package com.example.roomdatabase.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version =  1, exportSchema = false)
abstract class DatabaseSiswa : RoomDatabase(){
    abstract fun siswaDao() : SiswaDAO

    companion object {
        @Volatile
        private var instance: DatabaseSiswa? = null

        fun getDatabase(context : Context): DatabaseSiswa{
            return (instance?: synchronized(this){
                Room.databaseBuilder(
                    context, DatabaseSiswa::class.java,
                    "siswa_database")
                    .build().also {instance=it }
            })
        }
    }
}


