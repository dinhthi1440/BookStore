package com.example.bookstore.models

import com.google.gson.annotations.SerializedName

data class Province(
    val name: String,
    val type: String,
    val slug: String,
    @SerializedName("name_with_type")
    val nameWithType: String,
    val code: String,
)