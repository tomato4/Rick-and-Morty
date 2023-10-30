package cz.ackee.testtask.rm.repository.common.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCharacterResponseDto(
    val results: List<CharacterDto>
)
