package com.example.bookstore.ui.login

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.base.BaseFragment
import com.example.bookstore.databinding.FragmentLoginBinding
import com.example.bookstore.extensions.getUserID
import com.example.bookstore.extensions.openDlSuccess
import com.example.bookstore.extensions.saveUserID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.resume


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    override val viewModel by viewModel<LoginViewModel>()

    private val auth: FirebaseAuth by lazy { Firebase.auth }
    override fun initData() {
        if(sharedPreferences.getUserID()!=null && sharedPreferences.getUserID()!="" ){
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    override fun handleEvent() {
        binding.textvSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.textipEmail.text.toString()
            val password = binding.textipPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener { authResult ->
                        val userID = authResult.user?.uid ?: ""
                        val emailResult = authResult.user?.email ?: ""
                        sharedPreferences.saveUserID(userID)
                        val bundle = bundleOf("email" to emailResult)
                        context?.let { it1 -> dialog(it1).openDlSuccess() }
                        Handler(Looper.getMainLooper()).postDelayed({
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                        },2000)
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Đăng nhập thất bại, hãy thử lại",
                            Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun bindData() {
    }

    override fun destroy() {

    }
}