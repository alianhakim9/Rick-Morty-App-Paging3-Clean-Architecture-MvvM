package id.alianhakim.rickmortyapp.feature_character.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.alianhakim.rickmortyapp.commons.Resource
import id.alianhakim.rickmortyapp.feature_character.data.data_source.paging.CharactersPagingDataSource
import id.alianhakim.rickmortyapp.feature_character.domain.use_case.CharactersUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterUseCases: CharactersUseCases,
    private val charactersPagingDataSource: CharactersPagingDataSource
) : ViewModel() {
    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState: StateFlow<CharacterListState> = _uiState

    val listData = Pager(PagingConfig(pageSize = 1)) {
        charactersPagingDataSource
    }.flow.cachedIn(viewModelScope)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        characterUseCases.getCharactersUseCases().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _uiState.value =
                        CharacterListState(
                            isLoading = false,
                            characters = result.data?.characters ?: emptyList()
                        )
                }
                is Resource.Loading -> {
                    _uiState.value = CharacterListState(isLoading = true)
                }
                is Resource.Error -> {
                    _uiState.value = CharacterListState(
                        isLoading = false,
                        error = result.message ?: "some error"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            is CharacterListEvent.OnRetry -> {
                getCharacters()
            }
        }
    }
}