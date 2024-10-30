package com.example.rickymortyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.rickymortyapp.models.Character
import com.example.rickymortyapp.services.RickService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun DetailScreen(id: Int?, navController: NavController, innerPadding: PaddingValues) {
    var characterDetail by remember { mutableStateOf<Character?>(null) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(id) {
        if (id != null) {
            val BASE_URL = "https://rickandmortyapi.com/api/"
            val rickService = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickService::class.java)

            val character = rickService.getCharacterId(id)
            characterDetail = character
        }
    }

    characterDetail?.let { character ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.DarkGray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ${character.status}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Species: ${character.species}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Type: ${character.type.ifEmpty { "N/A" }}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Gender: ${character.gender}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Origin: ${character.origin.name}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Location: ${character.location.name}",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Appears in ${character.episode.size} episodes",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Text(
                text = "Created on: ${character.created}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
