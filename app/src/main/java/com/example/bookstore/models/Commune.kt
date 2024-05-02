package com.example.bookstore.models

import com.google.gson.annotations.SerializedName

data class Commune(
    val name: String,
    val type: String,
    val slug: String,
    @SerializedName("name_with_type")
    val nameWithType: String,
    val path: String,
    @SerializedName("path_with_type")
    val pathWithType: String,
    val code: String,
    @SerializedName("parent_code")
    val parentCode: String
)