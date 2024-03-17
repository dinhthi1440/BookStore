package com.example.bookstore.data.database

import androidx.room.RoomDatabase

abstract class DataBaseLocal : RoomDatabase() {
    companion object {
        const val VERSION = 1
    }
}