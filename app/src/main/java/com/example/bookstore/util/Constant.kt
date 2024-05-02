package com.example.bookstore.util

object Constant {
    private const val PROJECT_NAME = "com.example.evocab."
    const val SHARED_PREF_ROOT = PROJECT_NAME + "SHARED_PREF"

    const val SHARED_USER_ID = PROJECT_NAME + "USERID"
    const val SHARED_USER_ID_DEFAULT = ""
    const val SHARED_ADDRESS_ID = PROJECT_NAME + "ADDRESSID"
    const val SHARED_FIRST_TIME_LOGIN = PROJECT_NAME + "FIRST_TIME_LOG_IN"
    const val SHARED_ADDRESS_ID_DEFAULT = ""

    const val CHANNEL_ID = "notification_channel"
    const val CHANNEL_NAME = "com.example.bookstore"

    object Regex{
        const val passwordRegex = ".{8,}"
    }

}