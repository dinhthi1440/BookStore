package com.example.bookstore.extensions

import kotlin.random.Random

fun Random.generateIDPayment(): String{
    val random = Random
    val randomNumberPart = (1..10)
        .map { random.nextInt(0, 10) }
        .joinToString("")
    val randomCharPart = (1..10)
        .map { random.nextInt(97, 123).toChar() }
        .joinToString("")
    return "$randomNumberPart$randomCharPart"
}
fun Random.generateRandomCartID(): String {
    val random = Random
    val randomNumberPart = (1..4)
        .map { random.nextInt(0, 10) }
        .joinToString("")
    val randomCharPart = (1..6)
        .map { random.nextInt(97, 123).toChar() }
        .joinToString("")
    return "$randomNumberPart$randomCharPart"
}

fun Random.generateRandomRatingID(): String {
    val random = Random
    val randomNumberPart = (1..6)
        .map { random.nextInt(0, 10) }
        .joinToString("")
    val randomCharPart = (1..8)
        .map { random.nextInt(97, 123).toChar() }
        .joinToString("")
    return "$randomNumberPart$randomCharPart"
}

fun Random.generateRandomCustomerID(): String {
    val random = Random
    val randomNumberPart = (1..16)
        .map { random.nextInt(0, 10) }
        .joinToString("")
    return "$randomNumberPart"
}
fun Random.generateRandomCommentID(): String {
    val random = Random
    val randomNumberPart = (1..10)
        .map { random.nextInt(0, 10) }
        .joinToString("")
    return "comment$randomNumberPart"
}