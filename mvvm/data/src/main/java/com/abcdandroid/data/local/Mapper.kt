package com.abcdandroid.data.local

import android.util.SparseBooleanArray
import com.abcdandroid.data.model.dao.PassengerEntity

fun String.toPassengerEntity() = PassengerEntity(this)

fun PassengerEntity.toBusinessModel() = text

fun List<PassengerEntity>.toBusinessList(): List<String> = this.map { it.toBusinessModel() }