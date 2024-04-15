package com.example.bookstore.models

import java.io.Serializable

data class Friends(
    var friendIDs: List<String> = emptyList()
): Serializable{

}