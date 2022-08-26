package id.alianhakim.rickmortyapp.feature_character.domain.use_case

import id.alianhakim.rickmortyapp.feature_character.domain.use_case.get_character.GetCharacterUseCase
import id.alianhakim.rickmortyapp.feature_character.domain.use_case.get_characters.GetCharactersUseCase

data class CharactersUseCases(
    val getCharacterUseCase: GetCharacterUseCase,
    val getCharactersUseCases: GetCharactersUseCase
)
