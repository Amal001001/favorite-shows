package com.example.amal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DBShows::class], version = 1, exportSchema = false)
abstract class ShowsDatabase: RoomDatabase() {

    abstract fun showsDao(): ShowsDao

        companion object{
            @Volatile  // writes to this field are immediately visible to other threads
            private var INSTANCE: ShowsDatabase? = null

            fun getDatabase(context: Context): ShowsDatabase{
                val tempInstance = INSTANCE
                if(tempInstance != null){
                    return tempInstance
                }
                synchronized(this){  // protection from concurrent execution on multiple threads
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ShowsDatabase::class.java,
                        "shows_database"
                    ).fallbackToDestructiveMigration()  // Destroys old database on version change
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
}