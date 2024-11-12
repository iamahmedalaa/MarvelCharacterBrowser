package com.example.marvelcharacterbrowser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.marvelcharacterbrowser.userInterface.characterdetail.CharacterDetailScreen
import com.example.marvelcharacterbrowser.userInterface.characterdetail.CharacterDetailViewModel
import com.example.marvelcharacterbrowser.userInterface.characterlist.CharacterListScreen
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApp()
        }
    }
}

@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "characterList") {
        composable("characterList") {
            CharacterListScreen(onCharacterClick = { character ->
                navController.navigate("characterDetail/${character}")
            })
        }
        composable("characterDetail/{characterId}") { backStackEntry ->
            val viewModel: CharacterDetailViewModel = hiltViewModel()
            CharacterDetailScreen(viewModel)
        }
    }
}
