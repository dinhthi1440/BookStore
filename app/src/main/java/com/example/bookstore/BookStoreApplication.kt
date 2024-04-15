package com.example.bookstore

import android.app.Application
import android.util.Log
import com.example.bookstore.di.dataBaseModule
import com.example.bookstore.di.dataSourceModule
import com.example.bookstore.di.repositoryModule
import com.example.bookstore.di.sharedPreferencesModule
import com.example.bookstore.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookStoreApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Log.e("Đã chạy tới đây", "onCreate: ", )
        startKoin {
            androidContext(this@BookStoreApplication)
            modules(
                dataBaseModule,
                repositoryModule,
                viewModelModule,
                sharedPreferencesModule,
                dataSourceModule
            )
        }
    }

}