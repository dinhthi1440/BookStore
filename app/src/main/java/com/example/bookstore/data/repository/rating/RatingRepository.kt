package com.example.bookstore.data.repository.rating

import com.example.bookstore.base.BaseReponsitory
import com.example.bookstore.base.DataResult
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.models.Rating

class RatingRepository(private val cloud: IFirebaseSource.CloudStore): BaseReponsitory(), IRatingRepository {
    override suspend fun addRating(rating: Rating): DataResult<Int> {
        return getResult { cloud.addRating(rating) }
    }


}