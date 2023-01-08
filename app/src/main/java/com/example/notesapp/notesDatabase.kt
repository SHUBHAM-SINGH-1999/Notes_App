package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [note::class], version = 1)
abstract class notesDatabase:RoomDatabase() {
    abstract  fun notesDao(): notesDao

    companion object{
        @Volatile
        private var INSTANCE:notesDatabase? =null

        fun getnotes(context: Context):notesDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,notesDatabase::class.java,"Notes_db").build()
                }
            }
            return INSTANCE!!
        }
    }

}