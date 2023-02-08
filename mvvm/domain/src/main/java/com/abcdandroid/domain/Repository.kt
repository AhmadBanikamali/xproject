package com.abcdandroid.domain

interface Repository {

    suspend fun getSavedData(): List<String>

    suspend fun getRemoteData(pageNumber: Int): List<String>

    suspend fun addDataToFavorite(favorite: String)

}