package com.example.bookstore.data.service

import android.content.ContentValues
import android.util.Log
import com.example.bookstore.extensions.generateRandomCommentID
import com.example.bookstore.models.Book
import com.example.bookstore.models.BookGenres
import com.example.bookstore.models.Cart
import com.example.bookstore.models.Comment
import com.example.bookstore.models.CommentDetail
import com.example.bookstore.models.FriendEvaluation
import com.example.bookstore.models.Friends
import com.example.bookstore.models.Order
import com.example.bookstore.models.Rating
import com.example.bookstore.models.RatingDetail
import com.example.bookstore.models.TotalRateBook
import com.example.bookstore.models.User
import com.example.bookstore.models.Voucher
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

class CloudStoreSource: IFirebaseSource.CloudStore {
    private val db = Firebase.firestore
    override suspend fun getAllBook(): List<Book> {
        val listTypeBook: MutableList<Book> = mutableListOf()

        val result = db.collection("books").get().await()

        for (bookDocs in result) {
            val book = bookDocs.toObject<Book>()
            listTypeBook.add(book)

            for (genre in book.genres) {
                val book1 = listTypeBook.find { it.title == genre }
                    ?: Book().also { listTypeBook.add(it) }
                listTypeBook.add(book1)
            }
        }
        return listTypeBook
    }

    override suspend fun pushGenreBook(): Int {
        val listTypeBook: MutableList<String> = mutableListOf()

        val result = db.collection("books").get().await()

        for (bookDocs in result) {
            val book = bookDocs.toObject<Book>()
            for (genre in book.genres) {
                if (!listTypeBook.contains(genre)) {
                    listTypeBook.add(genre)
                }
            }
        }
        val genreCollection = Firebase.firestore.collection("genres")
        for (genre in listTypeBook) {
            genreCollection.add(mapOf("name" to genre))
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }
        return 0
    }

    override suspend fun addNewBook(book: Book): Int {
        val listGenre = db.collection("genres").get().await()
        var isExist = false
        for (genre in book.genres) {
            isExist = listGenre.any { it.toObject<String>() == genre }
            if (!isExist) {
                break
            }
        }
        if(!isExist){
            db.collection("genres").add(mapOf("name" to book.genres))
        }
        db.collection("books").add(book).addOnSuccessListener {
            Log.e("TAG", "addNewBook: Đã thêm thành công book và genre", )
        }.addOnFailureListener {
            Log.e("TAG", "addNewBook: Thêm thất bại book và genre", )
        }
        return 1
    }

    override suspend fun getBookByGenre(): List<BookGenres> {
        val listGenre = db.collection("books").get().await()
        val genreBooksMap = mutableMapOf<String, MutableList<Book>>()
        for (bookDocument in listGenre.documents) {
            val book = bookDocument.toObject<Book>()
            val genres = book?.genres ?: continue
            for (genre in genres) {
                val booksList = genreBooksMap.getOrPut(genre) { mutableListOf() }
                book.let { booksList.add(it) }
            }
        }
        return genreBooksMap.map { (genre, booksList) ->
            BookGenres(genre, booksList)
        }
    }

    override suspend fun getBookByID(bookID: String): Book {
        val book = db.collection("books").document(bookID).get().await()
        return book.toObject<Book>()!!
    }

    override suspend fun addNewCart(cart: Cart): Int {
        val result:Int = try {
            val documentRef = db.collection("user").document("carts")
                .collection(cart.userID).document(cart.cardID)
            documentRef.set(mapOf(
                "cardID" to cart.cardID,
                "userID" to cart.userID,
                "book" to cart.book,
                "quantity" to cart.quantity,
            )).await()
            1
        } catch (e: Exception) {
            0
        }
        return result
    }

    override suspend fun getCart(userID: String): List<Cart> {
        val listCartDocs = db.collection("user").document("carts")
            .collection(userID).get().await()
        val listCart: MutableList<Cart> = mutableListOf()
        for (cartDoc in listCartDocs) {
            val cart = cartDoc.toObject<Cart>()
            listCart.add(cart)
        }
        return listCart
    }

    override suspend fun deleteCarts(userID: String, listCardID: List<String>): Int {
        var result = 0
        for (cardID in listCardID){
            db.collection("user").document("carts")
                .collection(userID).document(cardID).delete()
                .addOnSuccessListener { result=1 }
                .addOnFailureListener {  result=0}
        }

        return result
    }

    override suspend fun addNewVoucher(voucher: Voucher): Int {
        var result:Int
        try {
            val documentRef = db.collection("voucher").document(voucher.voucherID)
            documentRef.set(mapOf(
                "voucherID" to voucher.voucherID,
                "voucherType" to voucher.voucherType,
                "description" to voucher.description,
                "expirationDate" to voucher.expirationDate,
                "icon" to voucher.icon,
                "voucherPercent" to voucher.voucherPercent,
                "minPrice" to voucher.minPrice,
                "maxDiscount" to voucher.maxDiscount
            )).await()
            result = 1
        } catch (e: Exception) {
            result = 0
        }
        return result
    }

    override suspend fun getVoucher(): List<Voucher> {
        val listVoucherDoc = db.collection("voucher").get().await()
        val listVoucher: MutableList<Voucher> = mutableListOf()
        for (voucherDoc in listVoucherDoc) {
            val voucher = voucherDoc.toObject<Voucher>()
            listVoucher.add(voucher)
        }
        return listVoucher
    }

    override suspend fun getOrder(customerID: String): List<Order> {
        val documentRef = db.collection("user").document("order")
            .collection(customerID).get().await()
        val listOrder: MutableList<Order> = mutableListOf()
        for (orderDoc in documentRef) {
            val order = orderDoc.toObject<Order>()
            listOrder.add(order)
        }
        return listOrder
    }

    override suspend fun addNewOrder(order: Order): Int {
        var result:Int

        try {
            val documentRef = db.collection("user").document("order")
                .collection(order.customerID).document(order.id)
            documentRef.set(mapOf(
                "id" to order.id,
                "customerID" to order.customerID,
                "orderStatus" to order.orderStatus,
                "listCart" to order.listCart,
                "totalPrice" to order.totalPrice,
                "totalTransportFee" to order.totalTransportFee,
                "totalPayment" to order.totalPayment,
                "voucher" to order.voucher,
                "payMethod" to order.payMethod,
                "orderDate" to order.orderDate,
                "shopAddress" to order.shopAddress,
                "customerAddress" to order.customerAddress,
                "customerPhoneNumber" to order.customerPhoneNumber,
                "customerName" to order.customerName
            )).await()
            result = 1
        } catch (e: Exception) {
            result = 0
        }
        return result
    }

    override suspend fun cancelOrder(customerID: String, order: Order): Int {
        var  result:Int

        try {
            val documentRef = db.collection("user").document("ordersCancel")
                .collection(order.customerID).document(order.id)
            order.orderStatus = "Đã huỷ"
            documentRef.set(mapOf(
                "id" to order.id,
                "customerID" to order.customerID,
                "orderStatus" to order.orderStatus,
                "listCart" to order.listCart,
                "totalPrice" to order.totalPrice,
                "totalTransportFee" to order.totalTransportFee,
                "totalPayment" to order.totalPayment,
                "voucher" to order.voucher,
                "payMethod" to order.payMethod,
                "orderDate" to order.orderDate,
                "shopAddress" to order.shopAddress,
                "customerAddress" to order.customerAddress,
                "customerPhoneNumber" to order.customerPhoneNumber,
                "customerName" to order.customerName
            )).await()
            result = 1
        } catch (e: Exception) {
            result = 0
        }
        db.collection("user").document("order")
            .collection(customerID).document(order.id).delete()
            .addOnSuccessListener { result = 1 }
            .addOnFailureListener { result = 0 }
        return result
    }

    override suspend fun getRating(bookID: String): List<RatingDetail> {
        val documentRef = db.collection("ratings").document("byBookID")
            .collection(bookID).get().await()
        val listRating: MutableList<RatingDetail> = mutableListOf()
        for (ratingDoc in documentRef) {
            val rating = ratingDoc.toObject<Rating>()
            val userDocRef = db.collection("user").document("information")
                .collection("byUserID").document(rating.customerID).get().await()
            val user = userDocRef.toObject<User>()!!
            listRating.add(RatingDetail(rating.id, user, rating))
        }
        return listRating
    }

    override suspend fun getNumberRateBook(bookID: String): TotalRateBook{
        val ratingDocRef=  db.collection("rateByNumberBook").document(bookID).get().await()
        return ratingDocRef.toObject<TotalRateBook>()!!
    }

    override suspend fun deleteRating(rating: Rating): Int {
        try {
            db.collection("ratings").document("byBookID")
                .collection(rating.bookID).document(rating.id).delete().await()

            db.collection("user").document("ratings")
                .collection(rating.customerID).document(rating.id).delete().await()

            val rateByNumberBookRef = db.collection("rateByNumberBook").document(rating.bookID)
            val documentSnapshot = rateByNumberBookRef.get().await()
            var isLast = 0
            for(i in 1..5){
                isLast +=documentSnapshot.get("rate${i}Star").toString().toInt()
            }
            if(isLast==1){
                db.collection("rateByNumberBook").document(rating.bookID).delete().await()
            }else{
                rateByNumberBookRef.update("rate${rating.numberRating}Star", FieldValue.increment(-1)).await()
            }
            return 1
        } catch (e: Exception) {
            Log.e("TAG", "addRating: $e")
            return 0
        }
    }

    override suspend fun updateRating(rating: Rating, userLikeID: String, isLike: Boolean): Int {
        try {
            if(isLike){
                val docRef = db.collection("ratings").document("byBookID")
                    .collection(rating.bookID).document(rating.id)
                docRef.update("likes", FieldValue.arrayUnion(userLikeID)).await()
                db.collection("user").document("ratings")
                    .collection(rating.customerID).document(rating.id)
                    .update("likes", FieldValue.arrayUnion(userLikeID)).await()
            }else{
                val docRef = db.collection("ratings").document("byBookID")
                    .collection(rating.bookID).document(rating.id)
                docRef.update("likes", FieldValue.arrayRemove(userLikeID)).await()
                db.collection("user").document("ratings")
                    .collection(rating.customerID).document(rating.id)
                    .update("likes", FieldValue.arrayRemove(userLikeID)).await()
            }
            return 1
        } catch (e: Exception) {
            return 0
        }
    }

    override suspend fun addUser(user: User): Int {
        var result:Int
        try {
            val documentRef = db.collection("user").document("information")
                .collection("byUserID").document(user.userID)
            documentRef.set(mapOf(
                "userID" to user.userID,
                "customerCode" to user.customerCode,
                "userName" to user.userName,
                "gender" to user.gender,
                "dateOfBirth" to user.dateOfBirth,
                "email" to user.email,
                "phoneNumber" to user.phoneNumber,
                "imageUser" to user.imageUser,
            )).await()
            result = 1
        } catch (e: Exception) {
            result = 0
        }

        return result
    }

    override suspend fun getUser(userID: String): User {
        val documentRef = db.collection("user").document("information")
            .collection("byUserID").document(userID).get().await()
        return documentRef.toObject<User>()!!
    }

    override suspend fun getListFriend(userID: String): MutableList<User> {
        var listComment: MutableList<User> = mutableListOf()
        val docFriendRef = db.collection("user").document("friends")
            .collection("byUserID").document(userID).get().await()
        val friendMap = docFriendRef.toObject<Friends>()!!
        for (friendID in friendMap.friendIDs) {
            val docUserRef = db.collection("user").document("information")
                .collection("byUserID").document(friendID).get().await()
            listComment.add(docUserRef.toObject<User>()!!)
        }
        return listComment
    }

    override suspend fun updateUser(user: User): Int {
        var result = 0
        db.collection("user").document("information")
            .collection("byUserID").document(user.userID).update("imageUser", user.imageUser)
            .addOnSuccessListener {
                result = 1
            }.addOnFailureListener {
                result=0
            }
        return result
    }

    override suspend fun updateInforUser(user: User): Int {
        var result = 0
        Log.e("TAG", "updateInforUser: $user", )
        db.collection("user").document("information")
            .collection("byUserID").document(user.userID)
            .set(mapOf(
                "userID" to user.userID,
                "customerCode" to user.customerCode,
                "userName" to user.userName,
                "gender" to user.gender,
                "dateOfBirth" to user.dateOfBirth,
                "email" to user.email,
                "phoneNumber" to user.phoneNumber,
                "imageUser" to user.imageUser,
            ))
            .addOnSuccessListener {
                result = 1
            }.addOnFailureListener {
                result=0
            }
            .await()
        return result
    }

    override suspend fun addComment(commentDetail: CommentDetail, rating: Rating): Int {
        var result:Int
        Log.e("TAG", "addComment: $commentDetail", )
        Log.e("TAG", "addComment: $rating", )
        try {
            val documentRef = db.collection("ratings").document("commentByRatingID")
                .collection(commentDetail.ratingID).document(commentDetail.id)
            documentRef.set(mapOf(
                "id" to commentDetail.id,
                "ratingID" to commentDetail.ratingID,
                "content" to commentDetail.content,
                "userID" to commentDetail.userID,
                "date" to commentDetail.date,
                "likes" to commentDetail.likes
            )).await()
            val docRef = db.collection("ratings").document("byBookID")
                .collection(rating.bookID).document(rating.id)
            docRef.update("comments", FieldValue.arrayUnion(commentDetail.id)).await()
            db.collection("user").document("ratings")
                .collection(rating.customerID).document(rating.id)
                .update("comments", FieldValue.arrayUnion(commentDetail.id)).await()
            result = 1

        } catch (e: Exception) {
            result = 0
        }
        return result
    }

    override suspend fun updateComment(commentDetail: CommentDetail, userLikeID: String, isLike: Boolean): Int {
        Log.e("TAG", "updateComment: ${commentDetail.ratingID} và ${commentDetail.id}", )
        try {
            if(isLike){
                db.collection("ratings").document("commentByRatingID")
                    .collection(commentDetail.ratingID).document(commentDetail.id)
                    .update("likes", FieldValue.arrayUnion(userLikeID))
            }else{
                db.collection("ratings").document("commentByRatingID")
                    .collection(commentDetail.ratingID).document(commentDetail.id)
                    .update("likes", FieldValue.arrayRemove(userLikeID))
            }
            return 1
        }catch (e: Exception){
            return 0
        }
    }

    override suspend fun deleteComment(commentDetailID: String, rating: Rating): Int {
        var result:Int

        try {
            val documentRef = db.collection("ratings").document("commentByRatingID")
                .collection(rating.id).document(commentDetailID)
            documentRef.delete().await()
            val docRef = db.collection("ratings").document("byBookID")
                .collection(rating.bookID).document(rating.id)
            docRef.update("comments", FieldValue.arrayRemove(commentDetailID))
            result = 1

        } catch (e: Exception) {
            result = 0
        }
        return result
    }

    override suspend fun getComment(rating: Rating): MutableList<Comment> {
        var listComment: MutableList<Comment> = mutableListOf()
        val documentRef = db.collection("ratings").document("commentByRatingID")
            .collection(rating.id).get().await()
        for (commentDetailDoc in documentRef) {
            val commentDetail = commentDetailDoc.toObject<CommentDetail>()
            val userDocRef = db.collection("user").document("information")
                .collection("byUserID").document(commentDetail.userID).get().await()
            val user = userDocRef.toObject<User>()
            val comment = Comment(Random.generateRandomCommentID(),  user!!, commentDetail)
            listComment.add(comment)
        }
        return listComment
    }

    override suspend fun addRating(rating: Rating): Int {
        try {
            val ratingsRef = db.collection("ratings").document("byBookID")
                .collection(rating.bookID).document(rating.id)
            ratingsRef.set(rating).await()

            val userRatingsRef = db.collection("user").document("ratings")
                .collection(rating.customerID).document(rating.id)
            userRatingsRef.set(rating).await()

            val rateByNumberBookRef = db.collection("rateByNumberBook").document(rating.bookID)
            val documentSnapshot = rateByNumberBookRef.get().await()

            if (documentSnapshot.exists()) {
                rateByNumberBookRef.update("rate${rating.numberRating}Star", FieldValue.increment(1)).await()
            } else {
                val rateValues = mutableListOf(0, 0, 0, 0, 0)
                rateValues[rating.numberRating - 1] = 1

                rateByNumberBookRef.set(
                    hashMapOf(
                        "id" to rating.id,
                        "bookID" to rating.bookID,
                        "rate1Star" to rateValues[0],
                        "rate2Star" to rateValues[1],
                        "rate3Star" to rateValues[2],
                        "rate4Star" to rateValues[3],
                        "rate5Star" to rateValues[4]
                    )
                ).await()
            }

            return 1
        } catch (e: Exception) {
            Log.e("TAG", "addRating: $e")
            return 0
        }
    }
    override suspend fun getFriendEvaluation(userID: String): MutableList<FriendEvaluation> {
        var listFriendEvaluation: MutableList<FriendEvaluation> = mutableListOf()
        val docFriendRef = db.collection("user").document("friends")
            .collection("byUserID").document(userID).get().await()
        val friendMap = docFriendRef.toObject<Friends>()!!
        for (friendID in friendMap.friendIDs){
            val documentRef = db.collection("user").document("ratings")
                .collection(friendID).get().await()
            val docUserRef = db.collection("user").document("information")
                .collection("byUserID").document(friendID).get().await()
            for (friendEvaluation in documentRef) {
                val evaluation = friendEvaluation.toObject<Rating>()
                val bookDocRef = db.collection("books").document(evaluation.bookID).get().await()
                val book = bookDocRef.toObject<Book>()
                listFriendEvaluation.add(FriendEvaluation(
                    evaluation.id,evaluation, book!!, docUserRef.toObject<User>()!!
                    )
                )
            }
        }
        return listFriendEvaluation
    }

    override suspend fun addFriends(userID: String, friendID: String): Int {
        return try {
            val documentRef = db.collection("user").document("friends")
                .collection("byUserID").document(userID)
            val documentSnapshot = documentRef.get().await()
            if (documentSnapshot.exists()) {
                documentRef.update("friendIDs", FieldValue.arrayUnion(friendID)).await()
            } else {
                documentRef.set(mapOf("friendIDs" to listOf(friendID))).await()
            }
            1
        } catch (e: Exception) {
            0
        }
    }

    override suspend fun deleteFriends(userID: String, friendID: String): Int {
        return try {
            val documentRef = db.collection("user").document("friends")
                .collection("byUserID").document(userID)
            documentRef.update("friendIDs", FieldValue.arrayRemove(friendID)).await()
            1
        } catch (e: Exception) {
            0
        }
    }
}