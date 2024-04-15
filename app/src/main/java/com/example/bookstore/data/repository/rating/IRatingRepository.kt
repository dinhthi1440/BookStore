package com.example.bookstore.data.repository.rating

import com.example.bookstore.base.DataResult
import com.example.bookstore.models.Rating

interface IRatingRepository {
    suspend fun addRating(rating: Rating): DataResult<Int>

}