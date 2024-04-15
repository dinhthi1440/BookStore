package com.example.bookstore.extensions

import android.content.SharedPreferences
import com.example.bookstore.untils.Constant

fun SharedPreferences.saveUserID(userID: String){
    this.edit().putString(Constant.SHARED_USER_ID, userID).apply()
}

fun SharedPreferences.getUserID(): String?{
    return this.getString(Constant.SHARED_USER_ID, Constant.SHARED_USER_ID_DEFAULT)
}
fun SharedPreferences.destroyUserID(): Boolean {
    return this.edit().remove(Constant.SHARED_USER_ID).commit()
}