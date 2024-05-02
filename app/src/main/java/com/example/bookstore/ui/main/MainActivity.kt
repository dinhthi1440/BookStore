package com.example.bookstore.ui.main

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseActivity
import com.example.bookstore.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate){


    private val navController by lazy { findNavController(R.id.nav_host_fragment_activity_main) }

    override fun initData() {

    }

    override fun handleData() {

    }

    override fun bindData() {
        setupWithNavController(binding.navView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> setNavigationVisible(true)
                R.id.friendsFragment -> setNavigationVisible(true)
                R.id.notifyFragment -> setNavigationVisible(true)
                R.id.settingFragment -> setNavigationVisible(true)
                else -> {
                    setNavigationVisible()
                }
            }
        }

    }
    private fun setNavigationVisible(isVisible: Boolean = false) {
        binding.navView.isVisible = isVisible
//        try {
//            Handler(Looper.getMainLooper()).postDelayed({
//                binding.navView.isVisible = isVisible
//            },500)
//        }catch (e: Exception){
//            binding.navView.isVisible = isVisible
//        }
    }


}