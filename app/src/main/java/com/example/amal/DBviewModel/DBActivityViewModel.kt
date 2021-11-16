package com.example.amal.DBviewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.amal.database.DBShows
import com.example.amal.database.ShowsDatabase
import com.example.amal.database.ShowsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DBActivityViewModel (application: Application): AndroidViewModel(application){
     private val repository: ShowsRepository
        private val shows: LiveData<List<DBShows>>

        init {
            val showsDao = ShowsDatabase.getDatabase(application).showsDao()
            repository = ShowsRepository(showsDao)
            shows = repository.getShows
        }

        fun readFromDB(): LiveData<List<DBShows>> {
                return shows
        }

        fun addShow(name: String, language: String, summary: String, imageUrl: String) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.addShow(DBShows(0, name, language, summary, imageUrl))
                readFromDB()
            }
        }

//        fun updateShow(id: Int,name: String, language: String, summary: String, imageUrl: String) {
//            CoroutineScope(Dispatchers.IO).launch {
//                repository.updateShow(DBShows(id,name, language, summary, imageUrl))
//                readFromDB()
//            }
//        }

        fun deleteShow(id: Int) {
            CoroutineScope(Dispatchers.IO).launch {
                repository.deleteShow(DBShows(id, "","","",""))
                readFromDB()
            }
        }
}