package com.example.bookstore.untils

object Constant {
    private const val PROJECT_NAME = "com.example.evocab."
    const val SHARED_PREF_ROOT = PROJECT_NAME + "SHARED_PREF"

    const val SHARED_USER_ID = PROJECT_NAME + "USERID"
    const val SHARED_USER_ID_DEFAULT = ""

    object Regex{
        const val passwordRegex = ".{8,}"
    }

}