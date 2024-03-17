package com.example.bookstore.ui.login

import android.app.Dialog
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.base.BaseViewModel
import com.example.bookstore.databinding.FragmentLoginBinding
import com.example.bookstore.extensions.openDlSuccess
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel: BaseViewModel
        get() = ViewModelProvider(this)[LoginViewModel::class.java]

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    override fun initData() {

    }

    override fun handleEvent() {

        binding.textvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            /*val email = binding.textipEmail.text.toString()
            val password = binding.textipPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(){
                        if(it.isSuccessful){
                            dialog?.openDlSuccess()
                            Handler(Looper.getMainLooper()).postDelayed({
                                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                            }, 2000)
                        }else{
                            Toast.makeText(context, "lỗi ${it.exception}", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
            }*/
        }
    }

    override fun bindData() {

    }

    override fun destroy() {

    }
}