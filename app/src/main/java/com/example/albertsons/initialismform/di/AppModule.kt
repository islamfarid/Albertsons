package com.example.albertsons.initialismform.di

import com.example.albertsons.initialismform.api.InitialismApi
import com.example.albertsons.initialismform.datasource.InitialismDataSource
import com.example.albertsons.initialismform.datasource.InitialismDataSourceImp
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun bindLoginDataSource(initialismDataSourceImp: InitialismDataSourceImp): InitialismDataSource

    companion object {
        @Provides
        @Reusable
        fun provideUsersApi(retrofit: Retrofit): InitialismApi =
            retrofit.create(InitialismApi::class.java)

        @Provides
        @Reusable
        @DefaultCoroutineDispatcher
        fun provideDefaultCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @Reusable
        @IOCoroutineDispatcher
        fun provideIOCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @ExperimentalSerializationApi
        private val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            explicitNulls = false
        }

        @ExperimentalSerializationApi
        @Provides
        @Singleton
        fun provideRetrofitClient(): Retrofit {
            val contentType = "application/json".toMediaType()
            val okHttpBuilder = OkHttpClient.Builder()
            val json = json.asConverterFactory(contentType)
            return Retrofit.Builder().baseUrl("http://www.nactem.ac.uk/")
                .addConverterFactory(json)
                .client(okHttpBuilder.build())
                .build()
        }
    }
}