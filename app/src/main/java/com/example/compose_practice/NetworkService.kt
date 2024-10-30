package com.example.compose_practice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

    // 포켓몬 이미지 받아오기
    @GET("pokemon/{pid}")
    fun getPokemonImages(
        @Path("pid") pid: Int
    ): Call<PokemonSprites>
}

data class PokemonResponse(
    val count: Int, // 얘네 굳이 써야하나?
    val next: String, // 얘네 굳이 써야하나?
    val previous: String, // 얘네 굳이 써야하나?
    val results: List<PokemonEntity>
)

data class PokemonEntity(
    val name: String,
    val url: String,
    var image: String? = null // 서버에서 처음에 받아오는 값 아님
)

data class PokemonSprites(
    val sprites: PokemonImage
)

data class PokemonImage(
    val front_default: String
)