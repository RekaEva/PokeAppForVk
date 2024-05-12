package com.example.pokeappforvk.data.network

import com.example.pokeappforvk.data.repository.PokemonRepositoryImpl
import com.example.pokeappforvk.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideRepository(api: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(api)
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(clientOkhttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(clientOkhttp)
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}

