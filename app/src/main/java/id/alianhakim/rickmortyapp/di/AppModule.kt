package id.alianhakim.rickmortyapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.alianhakim.rickmortyapp.commons.Constants.BASE_URL
import id.alianhakim.rickmortyapp.feature_character.data.data_source.paging.CharactersPagingDataSource
import id.alianhakim.rickmortyapp.feature_character.data.data_source.remote.RickMortyApi
import id.alianhakim.rickmortyapp.feature_character.data.data_source.repository.CharacterRepositoryImpl
import id.alianhakim.rickmortyapp.feature_character.domain.repository.CharacterRepository
import id.alianhakim.rickmortyapp.feature_character.domain.use_case.CharactersUseCases
import id.alianhakim.rickmortyapp.feature_character.domain.use_case.get_character.GetCharacterUseCase
import id.alianhakim.rickmortyapp.feature_character.domain.use_case.get_characters.GetCharactersUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickMortyApi(): RickMortyApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: RickMortyApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCharacterUseCase(repository: CharacterRepository): CharactersUseCases {
        return CharactersUseCases(
            getCharacterUseCase = GetCharacterUseCase(repository),
            getCharactersUseCases = GetCharactersUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCharatersPagingDataSource(repository: CharacterRepository): CharactersPagingDataSource {
        return CharactersPagingDataSource(repository)
    }
}