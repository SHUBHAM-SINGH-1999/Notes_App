package com.example.notesapp

import android.app.ActionBar
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), clickedonitem {
    lateinit var viewmodel:notesViewmodel
    lateinit var mainView: RecyclerView
    lateinit var list :ArrayList<note>
    lateinit var newlist:ArrayList<note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        mainView=findViewById(R.id.mainview)
        list= ArrayList()
        newlist= ArrayList()

        val dao = notesDatabase.getnotes(applicationContext).notesDao()
        val repo = notesRepo(dao)
        viewmodel = ViewModelProvider(this,notesViewmodalFactory(repo)).get(notesViewmodel::class.java)

        mainView.layoutManager=GridLayoutManager(this,2)
        mainView.adapter = notesAdapter(list,this)



        viewmodel.getnotes().observe(this){
           list = it as ArrayList<note>
            mainView.adapter = notesAdapter(list,this)
        }


    }

    override fun onitemclick(note: note) {
        viewmodel.delete_notes(note)
    }

    override fun onitemclick_open(note: note) {
        val intent = Intent(this,content::class.java)
        intent.putExtra("title",note.title)
        intent.putExtra("des",note.description)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_action)
        val SearchView = item?.actionView as SearchView

        SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                newlist.clear()
                for(i in list){
                    if(i.title.contains(p0!!)){
                        newlist.add(i)
                    }
                }
                mainView.adapter = notesAdapter(newlist,this@MainActivity)
                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    fun floation_Action(view: View) {
        val intent = Intent(this,content::class.java)
        startActivity(intent)
    }
}