package com.example.bookstore.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class JsonFileProcess {
    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
    inline fun <reified T> parseJsonToModel(jsonString: String): List<T> {
        val gson = Gson()
        val type = object : TypeToken<Map<String, T>>() {}.type
        val map: Map<String, T> = gson.fromJson(jsonString, type)
        return map.values.toList()
    }
}