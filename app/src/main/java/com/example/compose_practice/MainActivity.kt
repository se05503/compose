package com.example.compose_practice

import android.os.Bundle
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeCacheDir.setReadOnly()
        setContent {
            ComposePracticeTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    PokemonEx()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        PokemonEx()
    }
}

@Composable
fun PokemonEx() {
    val dummyPokemons = listOf(
        Pokemon(0, "포켓몬1", "http1", R.drawable.pokemon1),
        Pokemon(1, "포켓몬2", "http2", R.drawable.pokemon2),
        Pokemon(2, "포켓몬3", "http3", R.drawable.pokemon3)
    )
    LazyColumn {
        items(dummyPokemons) { dummyPokemon ->
            IndividualPokemonItem(dummyPokemon)
        }
    }
}

@Composable
fun IndividualPokemonItem(
    pokemon: Pokemon,
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
                            text = "포켓몬: ${pokemon.pokemonName}",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = pokemon.pokemonAddress,
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
            Card(modifier = Modifier.padding(8.dp)) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = pokemon.pokemonName)
                    Image(painter = painterResource(id = pokemon.pokemonImage), contentDescription = pokemon.pokemonName)
                    Button(onClick = {
                        navController.navigate("Home")
                    }) {
                        Text(text ="뒤로")
                    }
                }
            }
        }
    }
}

data class Pokemon(
    val id: Int,
    val pokemonName: String,
    val pokemonAddress: String,
    val pokemonImage: Int // server 연결할 때 type 을 String 으로 바꾸기
)





