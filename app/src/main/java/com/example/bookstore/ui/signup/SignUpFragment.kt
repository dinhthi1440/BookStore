package com.example.bookstore.ui.signup

import android.provider.CalendarContract.Colors
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment:BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate){
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[SingUpViewModel::class.java]

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    override fun initData() {

    }

    override fun handleEvent() {
        binding.textvLogin.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.textipEmail.text.toString()
            val password = binding.textipPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            binding.textipLayoutEmail.setBoxBackgroundColorResource(R.color.cus_green)
                            Toast.makeText (context, "Đăng ký thành công", Toast.LENGTH_SHORT).show()
                        } else {
                            binding.textipLayoutEmail.setBoxBackgroundColorResource(R.color.cus_orange_warning)
                        }
                    }
            }else{
                Toast.makeText (context, "Đăng ký lỗi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun bindData(){

    }

    override fun destroy() {

    }

}
