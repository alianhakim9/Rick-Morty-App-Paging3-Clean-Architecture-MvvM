package id.alianhakim.rickmortyapp.feature_character.presentation.character_list

import id.alianhakim.rickmortyapp.feature_character.domain.model.Character

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String = ""
)

