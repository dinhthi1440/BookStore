package com.example.bookstore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.data.repository.carts.ICartRepository
import com.example.bookstore.models.Book
import com.example.bookstore.models.Cart

class CartViewModel(private val cartRepository: ICartRepository):BaseViewModel() {
    private val _getResultCart = MutableLiveData<List<Cart>>()
    val getResultCart: LiveData<List<Cart>> get() = _getResultCart

    private val _getResultBook = MutableLiveData<Book>()
    val getResultBook: LiveData<Book> get() = _getResultBook
    fun getCart(userID: String){
        executeTask(
            request = {cartRepository.getCart(userID)},
            onSuccess = {
                _getResultCart.value = it
            },
            onError = {})
    }

    fun getBookByID(bookID: String){
        executeTask(
            request = {cartRepository.getBookByID(bookID)},
            onSuccess = {
                _getResultBook.value = it
            },
            onError = {}
        )
    }
}