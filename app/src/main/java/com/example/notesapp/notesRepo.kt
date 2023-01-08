package com.example.notesapp

import androidx.lifecycle.LiveData

class notesRepo(val notesdao: notesDao) {
    suspend fun insertnotes(note: note){
        notesdao.insertNote(note)
    }

    suspend fun deletenotes(note: note){
        notesdao.deleteNotes(note)
    }

    fun getnotes():LiveData<List<note>>{
       return notesdao.getnotes()
    }


}