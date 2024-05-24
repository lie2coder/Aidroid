package com.nj.framework.ktx

import android.text.InputFilter
import android.text.Spanned
import android.widget.EditText

/**
 * 为 EditText 添加一个 InputFilter 来阻止 Emoji 字符的输入
 * 返回原 EditText 实例，方便链式调用
 */
fun EditText.filterEmoji(): EditText {
    return addFilter { it.isEmojiCharacter() }
}

/**
 * 指定一个字符集，EditText 将阻止用户输入这些字符
 * 返回原 EditText 实例，方便链式调用
 */
fun EditText.filterCharacters(vararg characters: Char): EditText {
    return addFilter(characters.toSet()::contains)
}

/**
 * 为 EditText 添加一个新的 InputFilter
 * 返回原 EditText 实例，方便链式调用
 * @param filterPredicate 一个 Lambda 表达式，用于根据字符判断是否应该被过滤
 */
fun EditText.addFilter(filterPredicate: (Char) -> Boolean): EditText {
    filters += InputFilter { source, start, end, _, _, _ ->
        if (source is Spanned) {
            // 如果输入是 Spanned 类型，直接返回它，不对富文本内容进行过滤
            return@InputFilter source
        }
        // 遍历输入的字符序列
        for (i in start until end) {
            // 如果字符应该被过滤，则返回空字符串
            if (filterPredicate(source[i])) {
                return@InputFilter ""
            }
        }
        // 如果没有字符被过滤，则允许输入
        null
    }
    return this
}