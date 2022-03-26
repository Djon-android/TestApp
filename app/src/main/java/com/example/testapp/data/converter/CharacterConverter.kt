package com.example.testapp.data.converter

import androidx.room.TypeConverter

class CharacterConverter {
    @TypeConverter
    fun fromListToStringBuilder(list: List<String>): String {
        return list.joinToString { it }
    }

    @TypeConverter
    fun fromStringBuilderToList(string: String): List<String> {
        return string.split(", ")
    }
}