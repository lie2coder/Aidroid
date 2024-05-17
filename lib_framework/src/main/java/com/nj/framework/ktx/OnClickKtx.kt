package com.nj.framework.ktx

import android.os.SystemClock
import android.view.View
import com.nj.framework.R

/**
 * 设置长按点击监听器
 * @param block 返回的 Boolean 值表示长按事件是否已经被处理，如果返回 true，则表示事件已被消费；
 * 如果返回 false，则表示事件未被消费，可以继续传递给其他监听器
 */
fun View.onLongClick(block: (view: View) -> Boolean) {
    setOnLongClickListener { block.invoke(it) }
}

/**
 * 设置点击监听器，实现了防抖（debounce）功能
 * 在指定的等待时间（wait）内，即使用户多次点击，也只会执行一次 block 中的代码
 */
fun View.onClick(wait: Long = 200, block: (view: View) -> Unit) {
    setOnClickListener { view ->
        val currentTimeMillis = SystemClock.uptimeMillis()
        val lastClickTime = (view.getTag(R.id.click_time_stamp) as? Long) ?: 0
        if (currentTimeMillis - lastClickTime > wait) {
            view.setTag(R.id.click_time_stamp, currentTimeMillis)
            block(view)
        }
    }
}
