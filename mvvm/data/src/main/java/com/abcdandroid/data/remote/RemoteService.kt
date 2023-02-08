package com.abcdandroid.data.remote

import com.abcdandroid.data.model.DataItem
import com.abcdandroid.data.model.Passengers
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("passenger?size=10")
    suspend fun getPassengers(@Query("page") pageNumber: Int):Passengers

}