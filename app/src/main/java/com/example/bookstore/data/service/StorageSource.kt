package com.example.bookstore.data.service

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class StorageSource:IFirebaseSource.Storage {
    private var db = Firebase.storage.reference
    override suspend fun upLoadImage(path: String, uri: Uri): String {
        return suspendCoroutine { continuation ->
            try {
                db.child("image1/$path").putFile(uri)
                    .addOnSuccessListener { taskSnapshot ->
                        taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                            continuation.resume(uri.toString())
                        }.addOnFailureListener { exception ->
                            continuation.resumeWithException(exception)
                        }
                    }
                    .addOnFailureListener { exception ->
                        continuation.resumeWithException(exception)
                    }
            } catch (e: Exception) {
                continuation.resumeWithException(e)
            }
        }
    }
}