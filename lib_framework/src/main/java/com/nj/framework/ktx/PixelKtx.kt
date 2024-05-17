package com.nj.framework.ktx

import android.content.res.Resources
import android.util.TypedValue


/**
 * 将 Int 类型的 dp 值转换为像素值（Float 类型）
 */
fun Int.dp2px(): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)

/**
 * 将 Float 类型的 dp 值转换为像素值（Float 类型）
 */
fun Float.dp2px(): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

/**
 * 将 Int 类型的 dp 值转换为像素值（Int 类型）
 */
fun Int.dp2pxInt(): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics)
        .toInt()

/**
 * 将 Float 类型的 dp 值转换为像素值（Int 类型）
 */
fun Float.dp2pxInt(): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)
        .toInt()