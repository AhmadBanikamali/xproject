package com.abcdandroid.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.abcdandroid.data.local.passengerDao.PassengerDao
import com.abcdandroid.data.local.toBusinessList
import com.abcdandroid.data.local.toPassengerEntity
import com.abcdandroid.data.model.dao.PassengerEntity
import com.abcdandroid.data.remote.RemoteService
import com.abcdandroid.domain.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val remoteService: RemoteService,
    private val passengerDao: PassengerDao
    ) : Repository {


    override fun getSavedData(): LiveData<List<String>> {
        val items: LiveData<List<PassengerEntity>> = passengerDao.getItems()
        return Transformations.map(items){
            it.toBusinessList()
        }
    }

    override suspend fun getRemoteData(pageNumber: Int): List<String> {
        return remoteService.getPassengers(pageNumber).data?.map { it?.id?:"" }?: listOf()
    }

    override suspend fun addDataToFavorite(favorite: String) {
        passengerDao.addItem(favorite.toPassengerEntity())
    }

    override suspend fun removeDataFromFavorite(favorite: String) {
        passengerDao.removeItem(favorite.toPassengerEntity())
    }
}