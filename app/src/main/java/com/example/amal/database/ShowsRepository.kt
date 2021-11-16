package com.example.amal.database

import androidx.lifecycle.LiveData

class ShowsRepository(private val showsDao: ShowsDao) {

    val getShows: LiveData<List<DBShows>> = showsDao.getShows()

        suspend fun addShow(show: DBShows){
            showsDao.addShow(show)
        }

//        suspend fun updateShow(show: DBShows){
//            showsDao.updateShow(show)
//        }

        suspend fun deleteShow(show: DBShows){
            showsDao.deleteShow(show)
        }
}