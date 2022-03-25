package com.example.testapp.data.mapper

import com.example.testapp.data.database.CharacterDbModel
import com.example.testapp.data.network.model.CharacterDto
import com.example.testapp.data.network.model.LocationCharacterDto
import com.example.testapp.data.network.model.OriginDto
import com.example.testapp.domain.Character
import com.example.testapp.domain.LocationCharacter
import com.example.testapp.domain.Origin

class CharacterMapper {

    fun mapDtoToDbModel(dto: CharacterDto) = CharacterDbModel(
        idCharacter = dto.id,
        nameCharacter = dto.name,
        status = dto.status,
        species = dto.species,
        type = dto.species,
        gender = dto.species,
        origin = mapOriginDtoToEntity(dto.origin),
        location = mapLocationDtoToEntity(dto.location),
        image = dto.image,
        episode = dto.episode,
        url = dto.url,
        created = dto.created
    )

    fun mapDbModelToEntity(dbModel: CharacterDbModel) = Character(
        id = dbModel.idCharacter,
        name = dbModel.nameCharacter,
        status = dbModel.status,
        species = dbModel.species,
        type = dbModel.species,
        gender = dbModel.species,
        origin = dbModel.origin,
        location = dbModel.location,
        image = dbModel.image,
        episode = dbModel.episode,
        url = dbModel.url,
        created = dbModel.created
    )

    fun mapOriginDtoToEntity(dto: OriginDto): Origin = Origin(
        nameOrigin = dto.name,
        urlOrigin = dto.url
    )

    fun mapLocationDtoToEntity(dto: LocationCharacterDto): LocationCharacter = LocationCharacter(
        nameLocation = dto.name,
        urlLocation = dto.url
    )
}