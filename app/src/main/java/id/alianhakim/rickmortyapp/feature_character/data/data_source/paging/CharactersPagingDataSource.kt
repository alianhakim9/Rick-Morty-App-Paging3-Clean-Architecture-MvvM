package id.alianhakim.rickmortyapp.feature_character.data.data_source.paging

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.responses.toCharacterList
import id.alianhakim.rickmortyapp.feature_character.domain.model.Character
import id.alianhakim.rickmortyapp.feature_character.domain.repository.CharacterRepository
import javax.inject.Inject

class CharactersPagingDataSource @Inject constructor(
    private val repository: CharacterRepository
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: 1
            val data = repository.getCharacters(currentPage).toCharacterList()
            val responseData = mutableListOf<Character>()
            responseData.addAll(data.characters)

            val nextPageNumber: Int?
            val uri = Uri.parse(data.info.next)
            val nextPageQuery = uri.getQueryParameter("page")
            nextPageNumber = nextPageQuery?.toInt()

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}