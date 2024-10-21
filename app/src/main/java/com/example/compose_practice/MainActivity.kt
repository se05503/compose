package com.example.compose_practice

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
import androidx.compose.runtime.Composable
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
import com.example.compose_practice.ui.theme.ui.theme.ComposePracticeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    var pokemonItems = listOf<PokemonEntity>()

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
            offset = 20, limit = 40
        ).enqueue(object: Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if(response.isSuccessful) {
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
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
    navController: NavHostController = rememberNavController()
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
                        navController.navigate("Detail")
                    }) {
                        Text(text = "보기")
                    }
                }
            }
        }
        composable("Detail") {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Card(modifier = Modifier.padding(8.dp)) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = pokemon.name)
                        Image(
                            painter = painterResource(id = R.drawable.pokemon1),
                            contentDescription = pokemon.name
                        )
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




