package com.example.chucknorris.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.red
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
    private lateinit var viewModel: JokeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding = FragmentJokesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(JokeViewModel::class.java)
        return fragmentBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subscribeUi()
        subscribeObservers()
    }

    private fun subscribeUi() {
        fragmentBinding.reloadBt.setOnClickListener {
            reload()

        }
        adapter = MyAdapter()
        fragmentBinding.recyclerView.adapter = adapter
    }


    private fun reload() {
        val input: String = fragmentBinding.countEt.text.toString()
        if (fragmentBinding.countEt.text.toString() != ""){
            fragmentBinding.progressBar.visibility = View.VISIBLE
            val count = input.toInt()
            viewModel.getTheJoke(count)
        } else {
            fragmentBinding.countEt.error = "please enter number"

        }


    }

    private fun subscribeObservers() {
        viewModel.jokeLive.observe(viewLifecycleOwner, { q ->
            if (q != null) {
                val response = q.value
                val items: List<Items> = response
                adapter.setNewList(items)
                fragmentBinding.reloadBt.visibility = View.GONE
                fragmentBinding.countEt.visibility = View.GONE
                fragmentBinding.progressBar.visibility = View.GONE
            } else {
                Toast.makeText(requireContext(), "Please connect to the internet", Toast.LENGTH_SHORT).show()
                fragmentBinding.progressBar.visibility = View.GONE
            }
        })
    }
}




