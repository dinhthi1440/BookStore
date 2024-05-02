package com.example.bookstore.extensions

import android.content.SharedPreferences
import com.example.bookstore.util.Constant

fun SharedPreferences.saveUserID(userID: String){
    this.edit().putString(Constant.SHARED_USER_ID, userID).apply()
}

fun SharedPreferences.getUserID(): String?{
    return this.getString(Constant.SHARED_USER_ID, Constant.SHARED_USER_ID_DEFAULT)
}
fun SharedPreferences.destroyUserID(): Boolean {
    return this.edit().remove(Constant.SHARED_USER_ID).commit()
}

fun SharedPreferences.saveAddressID(addressID: String){
    this.edit().putString(Constant.SHARED_ADDRESS_ID, addressID).apply()
}

fun SharedPreferences.getAddressID(): String?{
    return this.getString(Constant.SHARED_ADDRESS_ID, Constant.SHARED_ADDRESS_ID_DEFAULT)
}
fun SharedPreferences.destroyAddressID(): Boolean {
    return this.edit().remove(Constant.SHARED_ADDRESS_ID).commit()
}

fun SharedPreferences.saveFirstLogIn(addressID: String){
    this.edit().putString(Constant.SHARED_FIRST_TIME_LOGIN, addressID).apply()
}

fun SharedPreferences.getFirstLogIn(): String?{
    return this.getString(Constant.SHARED_FIRST_TIME_LOGIN, Constant.SHARED_ADDRESS_ID_DEFAULT)
}
fun SharedPreferences.destroyFirstLogIn(): Boolean {
    return this.edit().remove(Constant.SHARED_ADDRESS_ID).commit()
}