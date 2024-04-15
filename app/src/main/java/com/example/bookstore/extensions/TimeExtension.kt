package com.example.bookstore.extensions

import java.util.Calendar


fun Calendar.getCurrentDateTime(): String {
    val currentDateTime = Calendar.getInstance()
    val day = currentDateTime.get(Calendar.DAY_OF_MONTH)
    val month = currentDateTime.get(Calendar.MONTH) + 1
    val year = currentDateTime.get(Calendar.YEAR)
    val hour = currentDateTime.get(Calendar.HOUR_OF_DAY)
    val minute = currentDateTime.get(Calendar.MINUTE)
    return "$day/$month/$year $hour:$minute"
}