package com.nj.framework.global

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences


lateinit var application: Application

const val SHARED_PREFERENCES_NAME = "sharedPreferences_aidroid"

val sharedPreferences: SharedPreferences by lazy {
    application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
}

/**
 * 向 SharedPreferences 写入数据
 * @param put 要执行的编辑操作，例如 putString、putInt 等
 */
fun writeSharedPreferences(put: SharedPreferences.Editor.() -> Unit) {
    val editor = sharedPreferences.edit()
    put.invoke(editor)
    editor.apply()
}

/**
 * 从 SharedPreferences 中读取数据
 */
inline fun <reified T> readSharedPreferences(
    key: String
): T? {
    return if (sharedPreferences.contains(key)) sharedPreferences.all[key]?.saveAs() else null
}

val clipboardManager: ClipboardManager by lazy {
    application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
}

/**
 * 将一段文本内容复制到剪贴板
 * @param label 在剪贴板中用于表示这段文本的标签，默认值为 "text"
 * @param text 要复制的文本内容
 */
fun writeClipboard(label: String = "text", text: String?) {
    val clip = ClipData.newPlainText(label, text)
    clipboardManager.setPrimaryClip(clip)
}

/**
 * 从剪贴板读取文本内容
 * @return 尝试获取剪贴板的当前内容，并返回第一个条目（如果有的话）作为字符串
 * 剪贴板中没有内容，或者当前应用没有权限访问剪贴板，这个函数将返回 null
 */
fun readClipboard(): String? {
    val clip = clipboardManager.primaryClip
    return clip?.getItemAt(0)?.text?.toString()
}
