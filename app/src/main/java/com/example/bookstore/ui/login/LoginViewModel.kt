package com.example.bookstore.ui.login

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.extensions.openDlSuccess

class LoginViewModel:BaseViewModel() {
//    fun login(email: String, password: String){
//        executeTask(
//            onSuccess = {}
//        )
//        if(email.isNotEmpty() && password.isNotEmpty()){
//            auth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(){
//                    if(it.isSuccessful){
//                        context?.let { it1 -> dialog(it1)?.openDlSuccess() }
//                        Handler(Looper.getMainLooper()).postDelayed({
//                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                        }, 2000)
//                    }else{
//                        Toast.makeText(context, "lỗi ${it.exception}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//        }else{
//        }
//    }
}