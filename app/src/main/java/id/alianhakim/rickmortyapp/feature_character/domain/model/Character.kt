package id.alianhakim.rickmortyapp.feature_character.domain.model

import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.Origin
import java.io.Serializable

data class Character(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
): Serializable