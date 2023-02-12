package com.abcdandroid.data.local.passengerDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.abcdandroid.data.local.entity.PassengerEntity

@Dao
interface Dao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItems(list: List<PassengerEntity>)
}
