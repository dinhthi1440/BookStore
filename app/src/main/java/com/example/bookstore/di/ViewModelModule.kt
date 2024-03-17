package com.example.bookstore.di

import com.example.bookstore.ui.login.LoginViewModel
import com.example.bookstore.ui.signup.SingUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel{LoginViewModel()}
    viewModel { SingUpViewModel() }
}