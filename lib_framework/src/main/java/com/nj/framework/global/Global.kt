package com.nj.framework.global

import android.app.Application
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import com.nj.framework.ktx.SHARED_PREFERENCES_NAME

/**
 * 提供全局的、单例的访问方式，以便在应用程序的不同部分中使用
 */


lateinit var application: Application

val sharedPreferences: SharedPreferences by lazy {
    application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
}

val clipboardManager: ClipboardManager by lazy {
    application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
}

