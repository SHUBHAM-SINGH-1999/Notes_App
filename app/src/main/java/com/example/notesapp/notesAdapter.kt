package com.example.notesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.floatingactionbutton.FloatingActionButton

class notesAdapter(val list: ArrayList<note>,val listener:clickedonitem): RecyclerView.Adapter<notesAdapter.notesViewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewholder {
        var inf = LayoutInflater.from(parent.context)
        var n = inf.inflate(R.layout.items,parent,false)
        var p = notesViewholder(n)
        p.delete.setOnClickListener {
            listener.onitemclick(list[p.adapterPosition])
        }
        p.mainitem.setOnClickListener{
            listener.onitemclick_open(list[p.adapterPosition])
        }
        return p
    }

    override fun onBindViewHolder(holder: notesViewholder, position: Int) {
        holder.title.text=list[position].title
        holder.des.text=list[position].description
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class notesViewholder(itemView: View) :ViewHolder(itemView){
     var title = itemView.findViewById<TextView>(R.id.title1)
     var des = itemView.findViewById<TextView>(R.id.des)
        var delete = itemView.findViewById<Button>(R.id.delete_button)
        var mainitem = itemView.findViewById<LinearLayout>(R.id.main_item)
    }
}

interface clickedonitem{
    fun onitemclick(note: note)
    fun onitemclick_open(note:note)
}