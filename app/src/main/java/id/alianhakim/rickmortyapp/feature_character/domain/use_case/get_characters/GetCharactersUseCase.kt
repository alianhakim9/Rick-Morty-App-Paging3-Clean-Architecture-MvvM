package id.alianhakim.rickmortyapp.feature_character.domain.use_case.get_characters

import id.alianhakim.rickmortyapp.commons.Resource
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.toCharacterList
import id.alianhakim.rickmortyapp.feature_character.domain.model.CharacterList
import id.alianhakim.rickmortyapp.feature_character.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(page: Int = 1): Flow<Resource<CharacterList>> {
        return flow {
            try {
                emit(Resource.Loading())
                val characters = repository.getCharacters(page).toCharacterList()
                emit(Resource.Success(characters))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage?.toString() ?: "an error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(e.localizedMessage?.toString() ?: "couldn't reach server"))
            }
        }
    }
}