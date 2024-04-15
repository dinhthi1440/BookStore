package com.example.bookstore.di

import com.example.bookstore.data.service.AuthenticationSource
import com.example.bookstore.data.service.CloudStoreSource
import com.example.bookstore.data.service.IFirebaseSource
import com.example.bookstore.data.service.StorageSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IFirebaseSource.CloudStore> { CloudStoreSource() }
    single<IFirebaseSource.Authentication> { AuthenticationSource() }
    single<IFirebaseSource.Storage> { StorageSource() }
}