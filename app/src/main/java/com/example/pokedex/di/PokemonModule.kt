package com.example.pokedex.di

import android.content.Context
import com.example.pokedex.data.PokeRepository
import com.example.pokedex.data.local.PokeDao
import com.example.pokedex.data.local.PokemonDB
import com.example.pokedex.data.remote.PokeRemoteDataSource
import com.example.pokedex.data.remote.PokeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(moshi: Moshi) : Retrofit = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/mrcsxsiq/b94dbe9ab67147b642baa9109ce16e44/raw/97811a5df2df7304b5bc4fbb9ee018a0339b8a38/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Singleton
    @Provides
    fun providePokeService(retrofit: Retrofit): PokeService {
        return retrofit.create(PokeService::class.java)
    }

    @Singleton
    @Provides
    fun providePokeRemoteDataSource(pokeService: PokeService) = PokeRemoteDataSource(pokeService)

    @Singleton
    @Provides
    fun providePokemonDB(@ApplicationContext context: Context) =PokemonDB.getDB(context)

    @Singleton
    @Provides
    fun providePokeDao(db: PokemonDB) = db.pokeDao()

    @Provides
    fun providePokeRepository(remoteDataSource: PokeRemoteDataSource,
                                localDataSource: PokeDao) =
    PokeRepository(remoteDataSource,localDataSource)
}