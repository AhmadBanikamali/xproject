package com.abcdandroid.data.model

import com.google.gson.annotations.SerializedName

data class PassengersDto(
	val totalPassengers: Int? = null,
	val data: List<DataItem?>? = null,
	val totalPages: Int? = null
)

data class AirlineItem(
	val established: String? = null,
	val country: String? = null,
	val website: String? = null,
	val name: String? = null,
	val headQuaters: String? = null,
	val logo: String? = null,
	val id: Int? = null,
	val slogan: String? = null
)

data class DataItem(
	val trips: Int? = null,
	val V: Int? = null,
	val name: String? = null,

	@SerializedName(value="_id")
	val id: String? = null,
	val airline: List<AirlineItem?>? = null
)

