package com.nj.framework.ktx

import android.content.SharedPreferences
import com.nj.framework.global.sharedPreferences

const val SHARED_PREFERENCES_NAME = "sharedPreferences_aidroid"

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