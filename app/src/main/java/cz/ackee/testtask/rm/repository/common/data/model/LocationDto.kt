package cz.ackee.testtask.rm.repository.common.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocationDto(
    val name: String,
    val url: String
)
