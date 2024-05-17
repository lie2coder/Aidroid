package com.nj.framework.ktx

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView

/**
 * 改变 CharSequence 中指定范围内文本的相对大小
 * @param range 要改变字体大小的文本范围
 * @param scale 文本相对大小的缩放比例，默认值为 1.5f
 * @return 返回一个 SpannableString 对象。这个 SpannableString 将应用一个 RelativeSizeSpan，
 * 该 span 会改变文本的相对大小，范围从 IntRange 的 first 到 last（包含 first，不包含 last）
 */
fun CharSequence.relativeSizeSpan(
    range: IntRange,
    scale: Float = 1.5f
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            RelativeSizeSpan(scale),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本的相对大小
 * @param str 要设置样式的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要改变字体大小的文本范围
 * @param scale 文本相对大小的缩放比例，默认值为 1.5f
 * @return 返回 TextView 对象，其中指定范围内的文本已经被应用了 RelativeSizeSpan
 */
fun TextView.relativeSizeSpan(
    str: String = "",
    range: IntRange,
    scale: Float = 1.5f
): TextView {
    text = (str.ifEmpty { text }).relativeSizeSpan(range, scale)
    return this
}

/**
 * 改变 CharSequence 中指定范围内文本的绝对大小
 * @param range 要改变字体大小的文本范围
 * @param textSize 要设置的字体大小（单位为 sp），默认值为 16
 * @return 返回一个 SpannableString 对象。这个 SpannableString 将应用一个 AbsoluteSizeSpan，
 * 该 span 会改变文本的绝对大小，范围从 IntRange 的 first 到 last（包含 first，不包含 last）
 */
fun CharSequence.absoluteSizeSpan(
    range: IntRange,
    textSize: Int = 16
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            AbsoluteSizeSpan(textSize.dp2pxInt()),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本的绝对大小
 * @param str 要设置样式的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要改变字体大小的文本范围
 * @param textSize 要设置的字体大小（单位为DP）
 * @return 返回 TextView 对象，其中指定范围内的文本已经被应用了 AbsoluteSizeSpan
 */
fun TextView.absoluteSizeSpan(
    str: String = "",
    range: IntRange,
    textSize: Int = 16
): TextView {
    text = (str.ifEmpty { text }).absoluteSizeSpan(range, textSize)
    return this
}

/**
 * 改变 CharSequence 中指定范围内文本的前景色
 * @param range 要改变文本颜色的文本范围
 * @param textColor 要设置的文本颜色，默认值为 Color.RED
 * @return 返回一个 SpannableString 对象。这个 SpannableString 将应用一个 ForegroundColorSpan，
 * 该 span 会改变文本的前景色，范围从 IntRange 的 first 到 last（包含 first，不包含 last）
 */
fun CharSequence.foregroundColorSpan(
    range: IntRange,
    textColor: Int = Color.RED
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            ForegroundColorSpan(textColor),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本的前景色
 * @param str 要设置样式的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要改变文本颜色的文本范围
 * @param color 要设置的文本颜色，默认值为 Color.RED
 * @return 返回应用了指定文本范围颜色的 TextView 对象
 */
fun TextView.foregroundColorSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED
): TextView {
    text = (str.ifEmpty { text }).foregroundColorSpan(range, color)
    return this
}

/**
 * 改变 CharSequence 中指定范围内文本的背景色
 * @param range 要改变背景色的文本范围
 * @param color 要设置的背景色，默认值为 Color.RED
 * @return 返回一个 SpannableString 对象。这个 SpannableString 将应用一个 BackgroundColorSpan，
 * 该 span 会改变文本的背景色，范围从 IntRange 的 first 到 last（包含 first，不包含 last）
 */
fun CharSequence.backgroundColorSpan(
    range: IntRange,
    color: Int = Color.RED
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            BackgroundColorSpan(color),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本的背景颜色
 * @param str 要设置背景颜色的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要改变背景颜色的文本范围
 * @param color 要设置的背景颜色，默认值为 Color.RED
 * @return 返回 TextView 对象，其中指定范围内的文本已经被应用了 BackgroundColorSpan
 */
fun TextView.backgroundColorSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED
): TextView {
    text = (str.ifEmpty { text }).backgroundColorSpan(range, color)
    return this
}

/**
 * 在 SpannableString 的指定范围内添加删除线样式
 * @param range 要添加删除线样式的文本范围
 * @return 返回一个 SpannableString 对象，其中指定范围内的文本已经被应用了 StrikethroughSpan
 */
fun CharSequence.strikethroughSpan(range: IntRange): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            StrikethroughSpan(),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本的删除线样式
 * @param str 要设置删除线样式的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要添加删除线样式的文本范围
 * @return 返回 TextView 对象，其中指定范围内的文本已经被应用了 StrikethroughSpan
 */
fun TextView.strikethroughSpan(
    str: String = "",
    range: IntRange
): TextView {
    text = (str.ifEmpty { text }).strikethroughSpan(range)
    return this
}

/**
 * 在 CharSequence 的指定范围内创建一个可点击的文本
 * @param range 要设置点击事件的文本范围
 * @param color 文本的颜色，默认值为 Color.RED
 * @param isUnderlineText 文本是否显示下划线，默认值为 false
 * @param clickAction 点击文本时执行的 lambda 表达式
 * @return 返回一个 SpannableString 对象，其中指定范围内的文本已经被应用了 ClickableSpan
 */
fun CharSequence.clickableSpan(
    range: IntRange,
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickAction: () -> Unit
): SpannableString {
    return SpannableString(this).apply {
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                clickAction()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = color
                ds.isUnderlineText = isUnderlineText
            }
        }
        setSpan(
            clickableSpan,
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本的可点击样式
 * @param str 要设置点击样式的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要设置点击样式的文本范围
 * @param color 点击文本的颜色，默认值为 Color.RED
 * @param isUnderlineText 文本是否显示下划线，默认值为 false
 * @param clickAction 点击文本时执行的 lambda 表达式
 * @return 返回 TextView 对象，其中指定范围内的文本已经被应用了 ClickableSpan
 */
fun TextView.clickableSpan(
    str: String = "",
    range: IntRange,
    color: Int = Color.RED,
    isUnderlineText: Boolean = false,
    clickAction: () -> Unit
): TextView {
    movementMethod = LinkMovementMethod.getInstance()
    highlightColor = Color.TRANSPARENT  // remove click bg color
    text = (str.ifEmpty { text }).clickableSpan(range, color, isUnderlineText, clickAction)
    return this
}

/**
 * 在 CharSequence 的指定范围内设置文本样式
 * @param range 要设置文本样式的文本范围
 * @param style 要应用的文本样式，默认值为 Typeface.BOLD
 * @return 返回一个 SpannableString 对象，其中指定范围内的文本已经被应用了 StyleSpan
 */
fun CharSequence.styleSpan(
    range: IntRange,
    style: Int = Typeface.BOLD
): SpannableString {
    return SpannableString(this).apply {
        setSpan(
            StyleSpan(style),
            range.first,
            range.last,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}

/**
 * 在 TextView 的指定范围内设置文本样式
 * @param str 要设置样式的文本，如果不提供或为空，则使用 TextView 当前的文本
 * @param range 要改变样式的文本范围
 * @param style 要应用的文本样式，默认值为 Typeface.BOLD
 * @return 回 TextView 对象，其中指定范围内的文本已经被应用了 StyleSpan
 */
fun TextView.styleSpan(
    str: String = "",
    range: IntRange,
    style: Int = Typeface.BOLD
): TextView {
    text = (str.ifEmpty { text }).styleSpan(range, style)
    return this
}
