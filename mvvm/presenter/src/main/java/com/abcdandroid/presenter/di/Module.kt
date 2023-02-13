package com.abcdandroid.presenter.di

import com.abcdandroid.domain.remote.GetData
import com.abcdandroid.presenter.datalist.adapter.PassengersAdapter
import com.abcdandroid.presenter.datalist.pagination.PassengersDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideAdapter() = PassengersAdapter()


    @Provides
    fun providePassengerDataSource(getData: GetData) = PassengersDataSource(getData = getData)
}