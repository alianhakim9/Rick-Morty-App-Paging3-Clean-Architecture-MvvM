package id.alianhakim.rickmortyapp.feature_character.presentation.character_list

sealed class CharacterListEvent {
    object OnRetry : CharacterListEvent()
}