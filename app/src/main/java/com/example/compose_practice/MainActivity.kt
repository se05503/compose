package com.example.compose_practice

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.compose_practice.ui.theme.ui.theme.ComposePracticeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    lateinit var pokemonItems: List<PokemonEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeCacheDir.setReadOnly()

        // server retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(NetworkService::class.java)
        retrofitService.getMultiplePagePokemonItems(
            offset = 0, limit = 40
        ).enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if (response.isSuccessful) {
                    // response message 가 아무것도 오지 않는다.
                    val response = response.body()
                    pokemonItems = response?.results ?: emptyList()
                    setContent {
                        ComposePracticeTheme {
                            Surface(
                                color = MaterialTheme.colors.background,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                PokemonEx(pokemonItems)
                            }
                        }
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "response is not successful",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "onFailure", Toast.LENGTH_SHORT).show()
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        PokemonEx(MainActivity().pokemonItems)
    }
}

@Composable
fun PokemonEx(pokemonItems: List<PokemonEntity>) {
    LazyColumn {
        items(pokemonItems) { pokemonItem ->
            IndividualPokemonItem(pokemonItem)
        }
    }
}

@Composable
fun IndividualPokemonItem(
    pokemon: PokemonEntity,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "포켓몬: ${pokemon.name}",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = pokemon.url,
                            color = Color.Gray
                        )
                    }
                    Button(onClick = {
                        val encodedUrl = Uri.encode(pokemon.url)
                        navController.navigate("Detail/$encodedUrl")
                    }) {
                        Text(text = "보기")
                    }
                }
            }
        }
        composable("Detail/{encodedUrl}") {
            var image by remember { mutableStateOf("") }
            val decodedUrl = Uri.decode(it.arguments?.getString("encodedUrl"))
            val pid = decodedUrl?.filter { url ->
                url.isDigit()
            }?.removePrefix("2")?.toInt() // 이렇게 받아오는건 아닌 것 같은데.. position 도 안되는 것 같고.. 다른 방법이 있을까?
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val networkService = retrofit.create(NetworkService::class.java)
            networkService.getPokemonImages(pid ?: 0).enqueue(object : Callback<PokemonSprites> {
                override fun onResponse(
                    call: Call<PokemonSprites>,
                    response: Response<PokemonSprites>
                ) {
                    if (response.isSuccessful) {
                        // 여기에 걸린다. 이미지 잘온다.
                        val response = response.body()
                        image = response!!.sprites.front_default // 여기는 이미지가 잘 오는데
                    }
                }

                override fun onFailure(call: Call<PokemonSprites>, t: Throwable) {
                    Log.d("response: ", "onFailure")
                }
            })
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = pokemon.name)
                        AsyncImage(
                            model = image,
                            contentDescription = pokemon.name,
                            modifier = Modifier.size(100.dp)
                        ) // 여기서는 이미지가 null 로 전달되네
                        Button(onClick = {
                            navController.navigate("Home")
                        }) {
                            Text(text = "뒤로")
                        }
                    }
                }
            }
        }
    }
}




