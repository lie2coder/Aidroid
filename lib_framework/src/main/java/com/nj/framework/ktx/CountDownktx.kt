package com.nj.framework.ktx

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

/**
 * 使用 Kotlin 协程和 Flow 实现的倒计时
 * @param total 倒计时的总时长，从这个数值开始递减到 0
 * @param scope 协程作用域，用于控制协程的生命周期
 * @param onTick 每次倒计时数值变化的回调，接收当前的倒计时数值
 * @param onStart 倒计时开始前的回调
 * @param onFinish 倒计时结束时的回调
 */
fun countDown(
    total: Int,
    scope: CoroutineScope,
    onTick: (Int) -> Unit,
    onStart: (() -> Unit)? = null,
    onFinish: (() -> Unit)? = null,
): Job {
    return flow {
        // 使用 for 循环从 total 开始递减到 0，并使用 emit 发射每一个数值
        for (i in total downTo 0) {
            emit(i)
            // delay(1000) 函数在每次发射后暂停 1 秒，模拟倒计时的递减
            delay(1000)
        }
    }
        // 在主线程上执行
        .flowOn(Dispatchers.Main)
        .onStart { onStart?.invoke() }
        .onCompletion { onFinish?.invoke() }
        .onEach { onTick.invoke(it) }
        .launchIn(scope)
}