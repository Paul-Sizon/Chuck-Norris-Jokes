package com.example.chucknorris.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.chucknorris.R
import com.example.chucknorris.network.Items
import com.example.chucknorris.network.retroApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JokesFragment : Fragment() {


    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jokes, container, false)
        val reloadButton = view.findViewById<Button>(R.id.reload_bt)
        val number = view.findViewById<EditText>(R.id.count_et)



        reloadButton.setOnClickListener {

            //GlobalScope.launch (Dispatchers.Main)

            lifecycleScope.launch {
                val count: Int = number.text.toString().toInt()
                val response = retroApi.api.getPost2(count)
                val items: List<Items> = response.value

                adapter = MyAdapter(items)
                val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
                recyclerView.adapter = adapter

                reloadButton.visibility = View.GONE
                number.visibility = View.GONE
            }
        }

        return view
    }


}


