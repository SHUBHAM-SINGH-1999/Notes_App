package com.example.notesapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class note(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,
    var title: String,
    var description: String
)