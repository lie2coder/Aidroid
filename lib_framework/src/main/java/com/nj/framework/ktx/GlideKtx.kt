package com.nj.framework.ktx

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.nj.framework.R
import com.nj.framework.manager.ActivityManager

/**
 * 加载网络图片到 ImageView。
 * @param url 图片的网络地址。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.load(
    url: String?,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .into(this)
}

/**
 * 加载本地资源图片到 ImageView。
 * @param resource 加载的本地资源 ID。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.load(
    @DrawableRes resource: Int,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .load(resource)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .into(this)
}

/**
 * 加载 Bitmap 图片到 ImageView。
 * @param bitmap 加载的 Bitmap 对象。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.load(
    bitmap: Bitmap,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .asBitmap()
        .load(bitmap)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .into(this)
}

/**
 * 加载 Drawable 图片到 ImageView。
 * @param drawable 加载的 Drawable 对象。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.load(
    drawable: Drawable,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .asDrawable()
        .load(drawable)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .into(this)
}

/**
 * 加载网络图片到 ImageView，裁剪为圆形。
 * @param url 图片的网络地址。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircular(
    url: String?,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    val options = RequestOptions.circleCropTransform()
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .apply(options)
        .into(this)
}

/**
 * 加载本地资源图片到 ImageView，裁剪为圆形。
 * @param resource 加载的本地资源 ID。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircular(
    @DrawableRes resource: Int,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    val options = RequestOptions.circleCropTransform()
    Glide.with(context)
        .load(resource)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .apply(options)
        .into(this)
}

/**
 * 加载 Bitmap 图片到 ImageView，裁剪为圆形。
 * @param bitmap 加载的 Bitmap 对象。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircular(
    bitmap: Bitmap,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    val options = RequestOptions.circleCropTransform()
    Glide.with(context)
        .asBitmap()
        .load(bitmap)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .apply(options)
        .into(this)
}

/**
 * 加载 Drawable 图片到 ImageView，裁剪为圆形。
 * @param drawable 加载的 Drawable 对象。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircular(
    drawable: Drawable,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    val options = RequestOptions.circleCropTransform()
    Glide.with(context)
        .asDrawable()
        .load(drawable)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .apply(options)
        .into(this)
}

/**
 * 加载网络图片到 ImageView，裁剪为圆角样式。
 * @param url 图片的网络地址。
 * @param radius 圆角半径
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadRounded(
    url: String?,
    radius: Int = 6,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CenterCrop(), RoundedCorners(radius.dp2pxInt()))
        .into(this)
}

/**
 * 加载本地资源图片到 ImageView，裁剪为圆角样式。
 * @param resource 加载的本地资源 ID。
 * @param radius 圆角半径
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadRounded(
    @DrawableRes resource: Int,
    radius: Int = 6,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .load(resource)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CenterCrop(), RoundedCorners(radius.dp2pxInt()))
        .into(this)
}

/**
 * 加载 Bitmap 图片到 ImageView，裁剪为圆角样式。
 * @param bitmap 加载的 Bitmap 对象。
 * @param radius 圆角半径
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadRounded(
    bitmap: Bitmap,
    radius: Int = 6,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .asBitmap()
        .load(bitmap)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CenterCrop(), RoundedCorners(radius.dp2pxInt()))
        .into(this)
}

/**
 * 加载 Drawable 图片到 ImageView，裁剪为圆角样式。
 * @param drawable 加载的 Drawable 对象。
 * @param radius 圆角半径
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadRounded(
    drawable: Drawable,
    radius: Int = 6,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .asDrawable()
        .load(drawable)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CenterCrop(), RoundedCorners(radius.dp2pxInt()))
        .into(this)
}

/**
 * 加载网络图片到 ImageView，裁剪为圆形带边框的样式。
 * @param url 图片的网络地址。
 * @param borderWidth 边框线条宽度。
 * @param borderColor 边框颜色。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircularBorder(
    url: String?,
    borderWidth: Float,
    borderColor: Int,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CircularBorderTransform(borderWidth, borderColor))
        .into(this)
}

/**
 * 加载本地资源图片到 ImageView，裁剪为圆形带边框的样式。
 * @param resource 加载的本地资源 ID。
 * @param borderWidth 边框线条宽度。
 * @param borderColor 边框颜色。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircularBorder(
    @DrawableRes resource: Int,
    borderWidth: Float,
    borderColor: Int,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .load(resource)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CircularBorderTransform(borderWidth, borderColor))
        .into(this)
}

/**
 * 加载 Bitmap 图片到 ImageView，裁剪为圆形带边框的样式。
 * @param bitmap 加载的 Bitmap 对象。
 * @param borderWidth 边框线条宽度。
 * @param borderColor 边框颜色。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircularBorder(
    bitmap: Bitmap,
    borderWidth: Float,
    borderColor: Int,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .asBitmap()
        .load(bitmap)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CircularBorderTransform(borderWidth, borderColor))
        .into(this)
}

/**
 * 加载 Drawable 图片到 ImageView，裁剪为圆形带边框的样式。
 * @param drawable 加载的 Drawable 对象。
 * @param borderWidth 边框线条宽度。
 * @param borderColor 边框颜色。
 * @param skipMemoryCache 是否跳过内存缓存。
 * @param placeholder 加载过程中显示的占位图资源 ID。
 * @param error 加载失败时显示的错误图资源 ID。
 * @param priority 加载优先级。
 */
fun ImageView.loadCircularBorder(
    drawable: Drawable,
    borderWidth: Float,
    borderColor: Int,
    skipMemoryCache: Boolean = true,
    @DrawableRes placeholder: Int = R.drawable.icon_placeholder,
    @DrawableRes error: Int = R.drawable.icon_placeholder,
    priority: Priority = Priority.HIGH
) {
    if (ActivityManager.isActivityDestroyed(context)) return
    Glide.with(context)
        .asDrawable()
        .load(drawable)
        .placeholder(placeholder)
        .error(error)
        .priority(priority)
        .skipMemoryCache(skipMemoryCache)
        .diskCacheStrategy(if (skipMemoryCache) DiskCacheStrategy.NONE else DiskCacheStrategy.RESOURCE)
        .transform(CircularBorderTransform(borderWidth, borderColor))
        .into(this)
}

/**
 * Transform that draws a border around a circle bitmap.
 */
class CircularBorderTransform(
    private val borderWidth: Float,
    private val borderColor: Int
) : CircleCrop() {

    private var borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = borderColor
        style = Paint.Style.STROKE
        strokeWidth = borderWidth
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        //因为继承自CircleCrop，并且CircleCrop是圆形，在这里获取的bitmap是裁剪后的圆形bitmap
        val transform = super.transform(pool, toTransform, outWidth, outHeight)
        val canvas = Canvas(transform)
        val halfWidth = (outWidth / 2).toFloat()
        val halfHeight = (outHeight / 2).toFloat()
        canvas.drawCircle(
            halfWidth,
            halfHeight,
            halfWidth.coerceAtMost(halfHeight) - borderWidth / 2,
            borderPaint
        )
        canvas.setBitmap(null)
        return transform
    }

}