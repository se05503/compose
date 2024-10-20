package com.example.compose_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_practice.ui.theme.ComposePracticeTheme

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
                    navigationEx()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        navigationEx()
    }
}

@Composable
fun navigationEx(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController() // = 다음은 기본값
) {
    NavHost(navController = navController, startDestination = "Home", modifier = modifier) {
        composable("Home") {
            Column {
                Text("Home 화면입니다.")
                Button(onClick = {navController.navigate("Office") {
                    popUpTo("Home") {
                        inclusive = true
                    }
                } }) {
                    Text("Office 로 이동하기")
                }
                Button(onClick = {navController.navigate("Playground") {
                    popUpTo("Home") {
                        inclusive = true
                    }
                } }) {
                    Text("Playground 로 이동하기")
                }
                Button(onClick = { navController.navigate("Home") {
                    launchSingleTop = true
                } }) {
                    Text("Home 로 이동하기")
                }
                Button(onClick = { navController.navigate("Data/parkseyoung") }) { // 슬래시 뒤에 전달할 데이터를 작성한다.
                    Text("Data 전달 화면으로 이동")
                }
            }
        }
        composable("Office") {
            Column {
                Text("Office 화면입니다.")
                Button(onClick = {navController.navigate("Home") {
                    popUpTo("Home") {
                        inclusive = true
                    }
                } }) {
                    Text("Home 로 이동하기")
                }
                Button(onClick = {navController.navigate("Playground") {
                    popUpTo("Home") {
                        inclusive = true
                    }
                } }) {
                    Text("Playground 로 이동하기")
                }
            }
        }
        composable("Playground") {
            Column {
                Text("Playground 화면입니다.")
                Button(onClick = {navController.navigate("Home") {
                    popUpTo("Home") {
                        inclusive = true
                    }
                } }) {
                    Text("Home 로 이동하기")
                }
                Button(onClick = {navController.navigate("Office") {
                    popUpTo("Home") {
                        inclusive = true
                    }
                } }) {
                    Text("Office 로 이동하기")
                }
            }
        }
        composable("Data/{username}") { // username = 전달받는 데이터의 key 값으로, 전달 받을 때는 중괄호를 쳐야한다.
            val receivedData = it.arguments?.getString("username")
            Text("전달받은 데이터: $receivedData")
        }
    }
}





