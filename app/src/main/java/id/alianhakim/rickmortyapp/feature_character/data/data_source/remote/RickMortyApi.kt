package id.alianhakim.rickmortyapp.feature_character.data.data_source.remote

import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.CharacterDto
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = 1
    ): CharacterListDto

    @GET("character/{id}")
    suspend fun getCharacter(id: Int): CharacterDto
}