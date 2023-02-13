package com.abcdandroid.data.model.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "passenger")
data class PassengerEntity(
    @PrimaryKey
    val text: String,
)
