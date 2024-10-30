package com.example.compose_practice

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.compose_practice.MainActivity.Companion.pokemonViewModel
import com.example.compose_practice.ui.theme.ui.theme.ComposePracticeTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {

    companion object {
        val pokemonViewModel = PokemonViewModel() // 한번만 생성한다.
    }

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

class PokemonViewModel : ViewModel() {
    private val _rawPokemonItems = mutableStateListOf<PokemonEntity>()

    private val _pokemonItems = MutableLiveData<List<PokemonEntity>>(_rawPokemonItems)
    val pokemonItems: LiveData<List<PokemonEntity>> = _pokemonItems

    val onReceiveListFromServer: (List<PokemonEntity>) -> Unit = {
        _rawPokemonItems.addAll(it)
        _pokemonItems.value = ArrayList(_rawPokemonItems)
    }

    val onChangeImage: (Int, String) -> Unit = { idx, imageSrc ->
        _rawPokemonItems[idx] = _rawPokemonItems[idx].copy(image = imageSrc)
        _pokemonItems.value = _rawPokemonItems.toMutableList()
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
                val pokemonItems = response.body()?.results
                if(pokemonItems!=null) {
                    pokemonViewModel.onReceiveListFromServer(pokemonItems)
                }
            }
        }

        override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
            Log.d("server response: ","onFailure")
        }
    })

    val pokemonItems = pokemonViewModel.pokemonItems.observeAsState(emptyList()).value
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
            val decodedUrl = Uri.decode(it.arguments?.getString("encodedUrl"))
            val pid = decodedUrl?.filter { url ->
                url.isDigit()
            }?.removePrefix("2")
                ?.toInt() // 이렇게 받아오는건 아닌 것 같은데.. position 도 안되는 것 같고.. 다른 방법이 있을까?
            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val networkService = retrofit.create(NetworkService::class.java)
            val pokemonList = pokemonViewModel.pokemonItems.observeAsState(emptyList()).value
            networkService.getPokemonImages(pid!!)
                .enqueue(object : Callback<PokemonSprites> {
                    override fun onResponse(
                        call: Call<PokemonSprites>,
                        response: Response<PokemonSprites>
                    ) {
                        if (response.isSuccessful) {
                            val response = response.body()
                            Log.d("list", pokemonList.toString())
                            if(pokemonList.isNotEmpty()) {
                                val index = pokemonList.indexOfFirst { it ->
                                    it.name == pokemon.name
                                }
                                pokemonViewModel.onChangeImage(index, response?.sprites!!.front_default)
                            }
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
                            model = pokemon.image,
                            contentDescription = pokemon.name,
                            modifier = Modifier.size(100.dp)
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





