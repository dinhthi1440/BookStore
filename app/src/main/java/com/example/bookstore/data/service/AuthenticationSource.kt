package com.example.bookstore.data.service

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.bookstore.models.User
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthenticationSource : IFirebaseSource.Authentication {
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val user = FirebaseAuth.getInstance().currentUser

    override suspend fun loginByEmailPass(email: String, password: String): String {
        return suspendCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    val userID = authResult.user?.uid ?: ""
                    val email = authResult.user?.email ?: ""
                    continuation.resume("$userID,$email")
                }
                .addOnFailureListener {
                    Log.e("TAG", "loginByEmailPass: $it", )
                    continuation.resume(",")
                }
        }
    }

    override suspend fun reAuthenticateUser(currentPassword: String): Int {
        return suspendCoroutine{continuation ->
            val credential = EmailAuthProvider.getCredential(user?.email!!, currentPassword)
            user.reauthenticate(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(1)
                    } else {
                        Log.e("TAG", "reAuthenticateUser: lỗi ${task.exception?.message}", )
                        continuation.resume(0)
                    }
                }
                .addOnFailureListener {
                    Log.e("TAG", "reAuthenticateUser: ${it}", )
                }

        }
    }

    override suspend fun updatePassWord(newPassword: String): Int {
        return suspendCoroutine { continuation ->
            if(user!=null){
                user.updatePassword(newPassword)
                    .addOnSuccessListener {
                        continuation.resume(1)
                    }
                    .addOnFailureListener {
                        continuation.resume(0)
                    }
            }else{
                continuation.resume(0)
            }
        }
    }
}