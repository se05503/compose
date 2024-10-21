package com.example.compose_practice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NetworkService {
    // 한 페이지를 보는 경우
    @GET("pokemon/")
    fun getOnePagePokemonItems(): Call<PokemonResponse>

    // 여러 페이지를 보는 경우
    @GET("pokemon/")
    fun getMultiplePagePokemonItems(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<PokemonResponse>
}



data class PokemonResponse(
    val count: Int, // 얘네 굳이 써야하나?
    val next: String, // 얘네 굳이 써야하나?
    val previous: String, // 얘네 굳이 써야하나?
    val results: List<PokemonEntity>
)

data class PokemonEntity(
    val name: String,
    val url: String
)