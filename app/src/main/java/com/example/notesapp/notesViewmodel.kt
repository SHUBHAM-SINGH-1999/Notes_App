package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class notesViewmodel(val notesRepo: notesRepo):ViewModel() {
    fun insert_notes(note: note){
        viewModelScope.launch(Dispatchers.IO){
           notesRepo.insertnotes(note)
        }
    }

    fun delete_notes(note: note){
        viewModelScope.launch(Dispatchers.IO){
            notesRepo.deletenotes(note)
        }
    }

    fun getnotes():LiveData<List<note>>{
       return notesRepo.getnotes()
    }

}