package com.nj.framework.ktx

import android.animation.Animator
import android.animation.IntEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Outline
import android.graphics.Path
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import androidx.recyclerview.widget.RecyclerView

/**
 * 设置View的圆角大小，支持链式调用
 * @param radiusDP 圆角半径
 */
fun View.radius(radiusDP: Int): View {
    if (radiusDP == 0) {
        clipToOutline = false
    } else {
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(
                view: View?,
                outLine: Outline?
            ) {
                outLine?.setRoundRect(
                    0,
                    0,
                    view?.width ?: 0,
                    view?.height ?: 0,
                    radiusDP.dp2px()
                )
            }
        }
    }
    return this
}


/**
 * 设置View的四个圆角大小，支持链式调用，未兼容低版本，仅Android API 31以上有效
 * @param leftTopRadius 左上角圆角半径
 * @param rightTopRadius 右上角圆角半径
 * @param rightBottomRadius 右下角圆角半径
 * @param leftBottomRadius 左下角圆角半径
 */
@SuppressLint("NewApi")
fun View.radius(
    leftTopRadius: Int = 0,
    rightTopRadius: Int = 0,
    rightBottomRadius: Int = 0,
    leftBottomRadius: Int = 0
): View {
    if (leftTopRadius == 0 && rightTopRadius == 0 && rightBottomRadius == 0 && leftBottomRadius == 0) {
        clipToOutline = false
    } else {
        clipToOutline = true
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View?, outline: Outline?) {
                val leftTop = leftTopRadius.dp2px()
                val rightTop = rightTopRadius.dp2px()
                val rightBottom = rightBottomRadius.dp2px()
                val leftBottom = leftBottomRadius.dp2px()
                val height = view?.height?.toFloat() ?: 166f
                val width = view?.width?.toFloat() ?: 43f
                val path = Path()
                if (leftTop > 0) {
                    path.moveTo(0f, leftTop)
                    path.arcTo(
                        0f,
                        0f,
                        leftTop * 2,
                        leftTop * 2,
                        -180f,
                        90f,
                        false
                    )
                } else {
                    path.moveTo(0f, 0f)
                }
                if (rightTop > 0) {
                    path.lineTo(width - rightTop, 0f)
                    path.arcTo(
                        width - rightTop * 2,
                        0f,
                        width,
                        rightTop * 2,
                        -90f,
                        90f,
                        false
                    )
                } else {
                    path.lineTo(width, 0f)
                }
                if (rightBottom > 0) {
                    path.lineTo(width, height - rightBottom)
                    path.arcTo(
                        width - rightBottom * 2,
                        height - rightBottom * 2,
                        width,
                        height,
                        0f,
                        90f,
                        false
                    )
                } else {
                    path.lineTo(width, height)
                }
                if (leftBottom > 0) {
                    path.lineTo(leftBottom, height)
                    path.arcTo(
                        0f,
                        height - leftBottom * 2,
                        leftBottom * 2,
                        height,
                        90f,
                        90f,
                        false
                    )
                } else {
                    path.lineTo(0f, height)
                }
                path.close()
                outline?.setPath(path)
            }
        }
    }
    return this
}

/**
 * 设置View的margin
 * @param leftMargin 默认保留原来的
 * @param topMargin 默认是保留原来的
 * @param rightMargin 默认是保留原来的
 * @param bottomMargin 默认是保留原来的
 */
fun View.margin(
    leftMargin: Int = Int.MAX_VALUE,
    topMargin: Int = Int.MAX_VALUE,
    rightMargin: Int = Int.MAX_VALUE,
    bottomMargin: Int = Int.MAX_VALUE
): View {
    val params = layoutParams as ViewGroup.MarginLayoutParams
    if (leftMargin != Int.MAX_VALUE) params.leftMargin = leftMargin
    if (topMargin != Int.MAX_VALUE) params.topMargin = topMargin
    if (rightMargin != Int.MAX_VALUE) params.rightMargin = rightMargin
    if (bottomMargin != Int.MAX_VALUE) params.bottomMargin = bottomMargin
    layoutParams = params
    return this
}

/**
 * 设置宽度，带有过渡动画
 * @param targetValue 目标宽度
 * @param duration 时长
 * @param action 可选行为
 */
fun View.animateWidth(
    targetValue: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
) {
    post {
        ValueAnimator.ofInt(width, targetValue).apply {
            addUpdateListener {
                width(it.animatedValue as Int)
                action?.invoke((it.animatedFraction))
            }
            if (listener != null) addListener(listener)
            setDuration(duration)
            start()
        }
    }
}

/**
 * 设置高度，带有过渡动画
 * @param targetValue 目标高度
 * @param duration 时长
 * @param action 可选行为
 */
fun View.animateHeight(
    targetValue: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
) {
    post {
        ValueAnimator.ofInt(height, targetValue).apply {
            addUpdateListener {
                height(it.animatedValue as Int)
                action?.invoke((it.animatedFraction))
            }
            if (listener != null) addListener(listener)
            setDuration(duration)
            start()
        }
    }
}

/**
 * 设置View的宽度
 */
fun View.width(width: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    layoutParams = params
    return this
}

/**
 * 设置View的高度
 */
fun View.height(height: Int): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.height = height
    layoutParams = params
    return this
}

/**
 * 设置宽度和高度，带有过渡动画
 * @param targetWidth 目标宽度
 * @param targetHeight 目标高度
 * @param duration 时长
 * @param action 可选行为
 */
fun View.animateWidthAndHeight(
    targetWidth: Int,
    targetHeight: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
) {
    post {
        val startHeight = height
        val evaluator = IntEvaluator()
        ValueAnimator.ofInt(width, targetWidth).apply {
            addUpdateListener {
                widthAndHeight(
                    it.animatedValue as Int,
                    evaluator.evaluate(it.animatedFraction, startHeight, targetHeight)
                )
                action?.invoke((it.animatedFraction))
            }
            if (listener != null) addListener(listener)
            setDuration(duration)
            start()
        }
    }
}

/**
 * 设置View的宽度和高度
 * @param width 要设置的宽度
 * @param height 要设置的高度
 */
fun View.widthAndHeight(
    width: Int,
    height: Int
): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.width = width
    params.height = height
    layoutParams = params
    return this
}

/**
 * 设置View高度，限制在min和max范围之内
 * @param h
 * @param min 最小高度
 * @param max 最大高度
 */
fun View.limitHeight(
    h: Int,
    min: Int,
    max: Int
): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    when {
        h < min -> params.height = min
        h > max -> params.height = max
        else -> params.height = h
    }
    layoutParams = params
    return this
}

/**
 * 设置View宽度，限制在min和max范围之内
 * @param w
 * @param min 最小宽度
 * @param max 最大宽度
 */
fun View.limitWidth(
    w: Int,
    min: Int,
    max: Int
): View {
    val params = layoutParams ?: ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    when {
        w < min -> params.width = min
        w > max -> params.width = max
        else -> params.width = w
    }
    layoutParams = params
    return this
}

/**
 * 获取View的截图, 支持获取整个RecyclerView列表的长截图
 * 注意：调用该方法时，请确保View已经测量完毕，如果宽高为0，则将抛出异常
 */
fun View.toBitmap(): Bitmap {
    if (measuredWidth == 0 || measuredHeight == 0) {
        throw RuntimeException("调用该方法时，请确保View已经测量完毕，如果宽高为0，则抛出异常以提醒！")
    }
    return when (this) {
        is RecyclerView -> {
            this.scrollToPosition(0)
            this.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )

            val bmp = Bitmap.createBitmap(width, measuredHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmp)

            //draw default bg, otherwise will be black
            if (background != null) {
                background.setBounds(0, 0, width, measuredHeight)
                background.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            this.draw(canvas)
            //恢复高度
            this.measure(
                View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.AT_MOST)
            )
            bmp //return
        }

        else -> {
            val screenshot =
                Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_4444)
            val canvas = Canvas(screenshot)
            if (background != null) {
                background.setBounds(0, 0, width, measuredHeight)
                background.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            draw(canvas)// 将 view 画到画布上
            screenshot //return
        }
    }
}