package com.example.bookstore.models

data class EvaluateUser(
    val id: String,
    val user: User,
    val commentDetailReply: MutableList<CommentDetail>,
    val rating: Rating
)
