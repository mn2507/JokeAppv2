package com.app.jokeappv2.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Muthu Narayanan on 25/11/2021.
 */
class DbConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}