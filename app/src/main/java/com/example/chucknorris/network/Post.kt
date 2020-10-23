package com.example.chucknorris.network

data class Post(
    val value: List<Items>

)

data class Items(
    val joke: String
)