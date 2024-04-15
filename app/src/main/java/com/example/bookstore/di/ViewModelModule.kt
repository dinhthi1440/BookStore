package com.example.bookstore.di

import com.example.bookstore.ui.account_infor.AccountInforVIewModel
import com.example.bookstore.ui.book_detail.BookDetailViewModel
import com.example.bookstore.ui.cart.CartViewModel
import com.example.bookstore.ui.change_password.ChangePassViewModel
import com.example.bookstore.ui.chatting.ChattingViewModel
import com.example.bookstore.ui.friends.FriendViewModel
import com.example.bookstore.ui.home.HomeViewModel
import com.example.bookstore.ui.login.LoginViewModel
import com.example.bookstore.ui.my_friends.ListFriendViewModel
import com.example.bookstore.ui.order.OrderViewModel
import com.example.bookstore.ui.pay.PayViewModel
import com.example.bookstore.ui.rating.RatingViewModel
import com.example.bookstore.ui.setting.SettingViewModel
import com.example.bookstore.ui.signup.SingUpViewModel
import com.example.bookstore.ui.voucher.VoucherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{LoginViewModel()}
    viewModel { SingUpViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { BookDetailViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { VoucherViewModel(get()) }
    viewModel { PayViewModel(get()) }
    viewModel { OrderViewModel(get()) }
    viewModel { RatingViewModel(get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { FriendViewModel(get()) }
    viewModel { ListFriendViewModel(get()) }
    viewModel { AccountInforVIewModel(get()) }
    viewModel { ChangePassViewModel(get()) }
    viewModel { ChattingViewModel(get()) }
}