package id.alianhakim.rickmortyapp.feature_character.domain.model

import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.Info

data class CharacterList(
    val info: Info,
    val characters: List<Character>
)
