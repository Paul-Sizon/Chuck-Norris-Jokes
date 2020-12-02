package com.example.chucknorris.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.network.Post
import com.example.chucknorris.network.retroApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class JokeViewModel(application: Application) : AndroidViewModel(application) {

    val jokeLive: MutableLiveData<Post> = MutableLiveData()


    fun getTheJoke(count: Int) = viewModelScope.launch(Dispatchers.Default) {
        try {
            val response = retroApi.api.getPost2(count)
            jokeLive.postValue(response)
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> {
                    jokeLive.postValue(null)
                }
            }
        }
    }


}