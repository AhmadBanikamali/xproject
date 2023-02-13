package com.abcdandroid.data.local.passengerDao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abcdandroid.data.model.dao.PassengerEntity

@Dao
interface PassengerDao{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(data: PassengerEntity)

    @Query("select * from passenger")
    fun getItems(): LiveData<List<PassengerEntity>>

    @Delete
    suspend fun removeItem(favorite: PassengerEntity)

}
