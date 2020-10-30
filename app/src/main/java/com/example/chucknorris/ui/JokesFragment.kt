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
import com.example.chucknorris.databinding.FragmentJokesBinding
import com.example.chucknorris.network.Items
import com.example.chucknorris.network.retroApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class JokesFragment : Fragment() {

    private lateinit var fragmentBinding: FragmentJokesBinding

    private lateinit var adapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentJokesBinding.inflate(inflater, container, false)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUi()
    }

    private fun subscribeUi() {
        fragmentBinding.reloadBt.setOnClickListener {
            reload()
        }
        adapter = MyAdapter()
        fragmentBinding.recyclerView.adapter = adapter
    }

    private fun reload() {
        lifecycleScope.launch {
            val count: Int = fragmentBinding.countEt.text.toString().toInt()
            val response = retroApi.api.getPost2(count)
            val items: List<Items> = response.value
            adapter.setNewList(items)
            fragmentBinding.reloadBt.visibility = View.GONE
            fragmentBinding.countEt.visibility = View.GONE
        }
    }
}


