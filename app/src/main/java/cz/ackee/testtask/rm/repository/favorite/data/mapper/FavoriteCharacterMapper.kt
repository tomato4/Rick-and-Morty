package cz.ackee.testtask.rm.repository.favorite.data.mapper

import cz.ackee.testtask.rm.repository.common.model.Character
import cz.ackee.testtask.rm.repository.favorite.data.model.FavoriteCharacter

fun FavoriteCharacter.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = "",
        type = null,
        gender = "",
        origin = "",
        location = "",
        imageUrl = imageUrl,
        favorite = true
    )
}

fun List<FavoriteCharacter>.toDomain(): List<Character> = this.map { it.toDomain() }

fun Character.toModel(): FavoriteCharacter {
    return FavoriteCharacter(
        id = id,
        name = name,
        status = status,
        imageUrl = imageUrl,
    )
}
