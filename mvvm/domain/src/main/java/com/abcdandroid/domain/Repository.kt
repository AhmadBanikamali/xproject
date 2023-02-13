package com.abcdandroid.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun getSavedData(): LiveData<List<String>>

    suspend fun getRemoteData(pageNumber: Int): List<String>

    suspend fun addDataToFavorite(favorite: String)

    suspend fun removeDataFromFavorite(favorite: String)

}