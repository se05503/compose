package com.example.compose_practice

import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {
    @GET("pokemon/")
    fun getPokemonItems(): Call<PokemonResponse>
}

data class PokemonResponse(
    val count: Int, // 얘네 굳이 써야하나?
    val next: String, // 얘네 굳이 써야하나?
    val previous: Boolean, // 얘네 굳이 써야하나?
    val results: List<PokemonEntity>
)

data class PokemonEntity(
    val name: String,
    val url: String
)