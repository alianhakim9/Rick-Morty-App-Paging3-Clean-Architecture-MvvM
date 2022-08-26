package id.alianhakim.rickmortyapp.feature_character.data.data_source.repository

import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.RickMortyApi
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.CharacterDto
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.CharacterListDto
import id.alianhakim.rickmortyapp.feature_character.domain.repository.CharacterRepository

class CharacterRepositoryImpl constructor(
    private val api: RickMortyApi
) : CharacterRepository {
    override suspend fun getCharacters(page: Int): CharacterListDto {
        return api.getCharacters(page)
    }

    override suspend fun getCharacter(id: Int): CharacterDto {
        return api.getCharacter(id)
    }
}