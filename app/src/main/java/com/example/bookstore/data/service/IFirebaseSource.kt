package com.example.bookstore.data.service

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.bookstore.models.Address
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Messages
import com.example.bookstore.models.Notify
import com.example.bookstore.models.Order
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.TotalRateBook
import com.example.bookstore.models.User
import com.example.bookstore.models.Voucher

interface IFirebaseSource {
    interface CloudStore{
        suspend fun getAllBook(): List<Book>
        suspend fun pushGenreBook(): Int
        suspend fun addNewBook(book: Book): Int
        suspend fun getBookByGenre(): List<BookGenres>
        suspend fun getBookByID(bookID: String): Book

        suspend fun addNewCart(cart: Cart): Int
        suspend fun getCart(userID: String): List<Cart>
        suspend fun deleteCarts(userID: String, listCardID: List<String>): Int

        suspend fun addNewVoucher(voucher: Voucher): Int
        suspend fun getVoucher(): List<Voucher>

        suspend fun getOrder(customerID: String): List<Order>
        suspend fun addNewOrder(order: Order): Int
        suspend fun cancelOrder(customerID: String, order: Order): Int

        suspend fun addRating(rating: Rating): Int
        suspend fun getRating(bookID: String): List<RatingDetail>
        suspend fun getNumberRateBook(bookID: String): TotalRateBook
        suspend fun deleteRating(rating: Rating): Int
        suspend fun updateRating(rating: Rating,userLikeID: String, isLike: Boolean): Int

        suspend fun addUser(user: User): Int
        suspend fun getUser(userID: String): User
        suspend fun getUserByCusID(customerID: String): User
        suspend fun getListFriend(userID: String): MutableList<User>

        suspend fun updateUser(user: User): Int
        suspend fun updateInforUser(user: User): Int


        suspend fun addComment(commentDetail: CommentDetail, rating: Rating): Int
        suspend fun updateComment(commentDetail: CommentDetail, userLikeID: String, isLike: Boolean): Int
        suspend fun getComment(rating: Rating): MutableList<Comment>
        suspend fun deleteComment(commentDetailID: String, rating: Rating): Int
        suspend fun getFriendEvaluation(userID: String): MutableList<FriendEvaluation>



        suspend fun addFriends(userID: String, friendID: String): Int
        suspend fun deleteFriends(userID: String, friendID: String): Int

        suspend fun addMessage(sender: String, receiver: String, message: String,
                               friendImage: String, friendName: String,userName: String): Int

        suspend fun addNewAddress(userID: String, address: Address): Int
        suspend fun deleteAddress(userID: String, address: Address): Int
        suspend fun getAddresses(userID: String): List<Address>
        suspend fun getNotifications(userID: String): List<Notify>

    }

    interface Authentication{
        suspend fun loginByEmailPass(email: String, password: String): String
        suspend fun reAuthenticateUser(currentPassword: String): Int
        suspend fun updatePassWord(newPassword: String): Int
    }
    interface Storage{
        suspend fun upLoadImage(path: String, uri: Uri): String
    }

}