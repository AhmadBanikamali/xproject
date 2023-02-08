package com.abcdandroid.di

import com.abcdandroid.data.RepositoryImpl
import com.abcdandroid.data.remote.RemoteService
import com.abcdandroid.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class Module {

    companion object {
        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.instantwebtools.net/v1/")
            .build()

        @Provides
        @Singleton
        fun provideRemoteService(retrofit: Retrofit): RemoteService = retrofit.create()
    }

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository


}