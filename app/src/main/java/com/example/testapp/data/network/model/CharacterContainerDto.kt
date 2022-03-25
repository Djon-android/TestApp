package com.example.testapp.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterContainerDto(
    @SerializedName("info")
    @Expose
    val characterInfoDto: CharacterInfoDto,

    @SerializedName("results")
    @Expose
    val characterDto: List<CharacterDto>? = null
)
