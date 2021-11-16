package com.example.amal.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShowsDao {
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun addShow(show: DBShows)

        @Query("SELECT * FROM ShowsTable ORDER BY id ASC")
        fun getShows(): LiveData<List<DBShows>>

   //     @Update
    //    suspend fun updateShow(show: DBShows)

        @Delete
        suspend fun deleteShow(show: DBShows)
}