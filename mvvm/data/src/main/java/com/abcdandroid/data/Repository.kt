package com.abcdandroid.data

import com.abcdandroid.data.remote.RemoteData
import com.abcdandroid.domain.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val remoteData: RemoteData) : Repository {
    override suspend fun getSavedData(): List<String> {
        return listOf()
    }

    override suspend fun getRemoteData(pageNumber: Int): List<String> {
        return remoteData.getData(pageNumber)
    }

    override suspend fun addDataToFavorite(favorite: List<String>) {

    }
}