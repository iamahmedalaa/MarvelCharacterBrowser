package com.example.marvelcharacterbrowser.userInterface.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.model.CharacterMarvel


import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter

@Composable
fun CharacterDetailScreen(viewModel: CharacterDetailViewModel = hiltViewModel()) {
    val character by viewModel.character.collectAsState()

    character?.let {
        CharacterDetailContent(characterMarvel = it)
    }
}

@Composable
fun CharacterDetailContent(characterMarvel: CharacterMarvel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = rememberImagePainter(characterMarvel.thumbnailUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = characterMarvel.name, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = characterMarvel.description,
            style = MaterialTheme.typography.body1,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}
