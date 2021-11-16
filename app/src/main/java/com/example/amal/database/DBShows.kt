package com.example.amal.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShowsTable")
data class DBShows(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val language: String,
    val summary: String,
    val imageUrl: String)
