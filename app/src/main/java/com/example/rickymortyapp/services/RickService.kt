package com.example.rickymortyapp.services

import com.example.rickymortyapp.models.Character
import com.example.rickymortyapp.models.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterId(@Path("id") id: Int): Character
}
