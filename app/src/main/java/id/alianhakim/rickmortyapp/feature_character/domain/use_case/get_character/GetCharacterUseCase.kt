package id.alianhakim.rickmortyapp.feature_character.domain.use_case.get_character

import id.alianhakim.rickmortyapp.commons.Resource
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.toCharacter
import id.alianhakim.rickmortyapp.feature_character.domain.model.Character
import id.alianhakim.rickmortyapp.feature_character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<Character>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = repository.getCharacter(id).toCharacter()
                emit(Resource.Success(response))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage?.toString() ?: "an error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage?.toString() ?: "couldn't reach server"))
            }
        }
    }
}