package com.example.testapp.data.network.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

data class CharacterInfoDto(
    @SerializedName("count")
    @Expose
    private var count: Int? = null,

    @SerializedName("pages")
    @Expose
    private var pages: Int? = null,

    @SerializedName("next")
    @Expose
    private var next: String? = null,

    @SerializedName("prev")
    @Expose
    private var prev: String? = null
)


