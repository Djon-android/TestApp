package com.example.testapp.data.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.domain.LocationCharacter
import com.example.testapp.domain.Origin

@Entity(tableName = "character")
data class CharacterDbModel(
    @PrimaryKey
    val idCharacter: Int,
    val nameCharacter: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @Embedded
    val origin: Origin,
    @Embedded
    val location: LocationCharacter,
    val image: String,
    val episode: List<String>?,
    val url: String,
    val created: String
)