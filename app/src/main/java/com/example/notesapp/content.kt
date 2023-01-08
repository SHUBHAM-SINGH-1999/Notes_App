package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class content : AppCompatActivity() {

    lateinit var submit_button: Button
    lateinit var title_text:EditText
    lateinit var des_text:EditText
    lateinit var viewmodel:notesViewmodel
    lateinit var title:String
    lateinit var des:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        submit_button = findViewById(R.id.submit_Button)
        title_text = findViewById(R.id.ed_title)
        des_text = findViewById(R.id.ed_description)

        val dao = notesDatabase.getnotes(applicationContext).notesDao()
        val repo = notesRepo(dao)
        viewmodel = ViewModelProvider(this, notesViewmodalFactory(repo)).get(notesViewmodel::class.java)

        title = intent.getStringExtra("title").toString()
        des = intent.getStringExtra("des").toString()

        if(title=="null") title = ""
        if(des=="null") des=""

        title_text.setText(title)
        des_text.setText(des)


        submit()
    }


    fun submit(){
        submit_button.setOnClickListener {
            if (title_text.text.isEmpty() && des_text.text.isEmpty()) {
                Toast.makeText(this, "Enter the data!", Toast.LENGTH_SHORT).show()
            } else {
                viewmodel.insert_notes(
                    note(
                        null,
                        title_text.editableText.toString(),
                        des_text.editableText.toString()
                    )
                )
                Toast.makeText(this, "Notes Added", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}