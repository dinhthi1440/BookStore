package com.example.bookstore.di

import com.example.bookstore.data.repository.book_detail.BookDetailRepository
import com.example.bookstore.data.repository.book_detail.IBookDetailRepository
import com.example.bookstore.data.repository.carts.CartRepository
import com.example.bookstore.data.repository.carts.ICartRepository
import com.example.bookstore.data.repository.friends.FriendRepository
import com.example.bookstore.data.repository.friends.IFriendRepository
import com.example.bookstore.data.repository.home.HomeRepository
import com.example.bookstore.data.repository.home.IHomeRepository
import com.example.bookstore.data.repository.login.ILoginRepository
import com.example.bookstore.data.repository.login.LoginRepository
import com.example.bookstore.data.repository.my_friends.IMyFriendRepository
import com.example.bookstore.data.repository.my_friends.MyFriendRepository
import com.example.bookstore.data.repository.order.IOrderRepository
import com.example.bookstore.data.repository.order.OrderRepository
import com.example.bookstore.data.repository.pay.IPayRepository
import com.example.bookstore.data.repository.pay.PayRepository
import com.example.bookstore.data.repository.rating.IRatingRepository
import com.example.bookstore.data.repository.rating.RatingRepository
import com.example.bookstore.data.repository.setting.ISettingRepository
import com.example.bookstore.data.repository.setting.SettingRepository
import com.example.bookstore.data.repository.sign_up.ISignUpRepository
import com.example.bookstore.data.repository.sign_up.SignUpRepository
import com.example.bookstore.data.repository.voucher.IVoucherRepository
import com.example.bookstore.data.repository.voucher.VoucherRepository
import org.koin.dsl.module

val repositoryModule = module{
    single<IHomeRepository> { HomeRepository(get()) }
    single<IBookDetailRepository> { BookDetailRepository(get()) }
    single<ICartRepository> { CartRepository(get()) }
    single<IVoucherRepository> { VoucherRepository(get()) }
    single<IPayRepository> { PayRepository(get()) }
    single<IOrderRepository> { OrderRepository(get()) }
    single<IRatingRepository> { RatingRepository(get()) }
    single<ILoginRepository> { LoginRepository(get()) }
    single<ISettingRepository> { SettingRepository(get(), get(), get()) }
    single<IFriendRepository> { FriendRepository(get()) }
    single<ISignUpRepository> { SignUpRepository(get()) }
    single<IMyFriendRepository> { MyFriendRepository(get()) }
}