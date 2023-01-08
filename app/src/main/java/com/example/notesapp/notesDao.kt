package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface notesDao {
    @Insert
    suspend fun insertNote(note: note)

    @Delete
    suspend fun deleteNotes(note: note)

    @Query("SELECT * FROM notes")
    fun getnotes(): LiveData<List<note>>


}