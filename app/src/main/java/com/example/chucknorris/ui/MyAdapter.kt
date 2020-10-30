package com.example.chucknorris.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorris.R
import com.example.chucknorris.network.Items
import com.example.chucknorris.network.Post


class MyAdapter() :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var jokesList: List<Items> = emptyList()

    fun setNewList(newList: List<Items>) {
        jokesList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.items_layout,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = jokesList[position]

        holder.jokeChuck.text = currentItem.joke
    }

    override fun getItemCount() = jokesList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val jokeChuck: TextView = itemView.findViewById(R.id.joke_card)

        fun onBind(){

        }
    }
}