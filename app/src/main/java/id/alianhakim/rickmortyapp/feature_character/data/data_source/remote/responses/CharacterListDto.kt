package id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses


import com.google.gson.annotations.SerializedName
import id.alianhakim.rickmortyapp.feature_character.domain.model.CharacterList

data class CharacterListDto(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val results: List<CharacterDto>
)

fun CharacterListDto.toCharacterList(): CharacterList {
    val res = results.map {
        it.toCharacter()
    }
    return CharacterList(info, res)
}