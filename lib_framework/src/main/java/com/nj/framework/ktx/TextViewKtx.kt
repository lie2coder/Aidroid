package com.nj.framework.ktx

import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginStart

/**
 * 计算并返回 TextView 中文本占用的总宽度，包括文本内容的宽度、内边距和外边距
 */
fun TextView.getViewWidth(): Float {
 return paint.measureText(text.toString()) + paddingStart + paddingEnd + marginStart + marginEnd
}

/**
 * 给 TextView 中的文本添加删除线效果
 */
fun TextView.strikeThroughText() {
 paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
}

/**
 * 取消 TextView 中文本的删除线效果
 */
fun TextView.cancelStrikeThroughText() {
 paintFlags = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

/**
 * 使 TextView 中的文本呈现为粗体样式（加小粗）
 */
fun TextView.bold() {
 paint.isFakeBoldText = true
}

/**
 * 设置 TextView 的字体样式为加粗（加大粗）
 */
fun TextView.Bold() {
 typeface = Typeface.defaultFromStyle(Typeface.BOLD)
}