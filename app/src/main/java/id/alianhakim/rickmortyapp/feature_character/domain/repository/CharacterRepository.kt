package id.alianhakim.rickmortyapp.feature_character.domain.repository

import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.CharacterDto
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.CharacterListDto

interface CharacterRepository {
    suspend fun getCharacters(page: Int): CharacterListDto
    suspend fun getCharacter(id: Int): CharacterDto
}