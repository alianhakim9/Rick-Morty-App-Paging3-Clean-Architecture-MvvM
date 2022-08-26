package id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)