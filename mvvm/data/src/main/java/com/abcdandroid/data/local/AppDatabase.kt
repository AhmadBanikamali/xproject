package com.abcdandroid.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abcdandroid.data.local.passengerDao.PassengerDao
import com.abcdandroid.data.model.dao.PassengerEntity

@Database(entities = [PassengerEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getPassengersDao(): PassengerDao
}