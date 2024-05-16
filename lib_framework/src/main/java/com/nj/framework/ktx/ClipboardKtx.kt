package com.nj.framework.ktx

import android.content.ClipData
import com.nj.framework.global.clipboardManager

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
