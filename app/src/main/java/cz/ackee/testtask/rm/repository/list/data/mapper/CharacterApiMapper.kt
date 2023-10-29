package cz.ackee.testtask.rm.repository.list.data.mapper

import cz.ackee.testtask.rm.repository.common.data.model.CharacterDto
import cz.ackee.testtask.rm.repository.common.model.Character

fun CharacterDto.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.name,
        location = location.name,
        imageUrl = image,
        favorite = false
    )
}

fun List<CharacterDto>.toDomain(): List<Character> =
    this.map { it.toDomain() }