package com.example.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class notesViewmodalFactory(val notesRepo: notesRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return notesViewmodel(notesRepo) as T
    }
}