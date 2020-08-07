package com.experion.assignment.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.experion.assignment.models.CaModel

class Converters {
    @TypeConverter
    fun fromArrayLisr(value: List<CaModel>?) = Gson().toJson(value)

    @TypeConverter
    fun fromString(value: String) = Gson().fromJson(value, Array<CaModel>::class.java).toList()
}