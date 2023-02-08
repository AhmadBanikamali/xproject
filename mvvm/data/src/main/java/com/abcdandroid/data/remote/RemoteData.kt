package com.abcdandroid.data.remote

import javax.inject.Inject


class RemoteData @Inject constructor(private val remoteService: RemoteService) {

    suspend fun getData(pageNumber: Int): List<String> {
        return remoteService.getPassengers(pageNumber).data?.map {
            it?.name ?: "null"
        } ?: listOf("aaa")
    }

}