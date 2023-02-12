package com.abcdandroid.data.local.entity

import androidx.room.PrimaryKey

data class PassengerEntity(
    @PrimaryKey
    val id: Int,
    val text: String,
)
