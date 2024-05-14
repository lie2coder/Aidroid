package com.nj.framework.ktx


/**
 * 尝试将任一类型的对象转换为泛型类型 T
 * 这个函数使用了 reified 关键字，这意味着它在运行时知道 T 的具体类型，并且会进行类型检查
 * 如果转换不成功，它会在运行时抛出一个 ClassCastException
 */
inline fun <reified T> Any.saveAs(): T {
    return this as T
}

/**
 * 尝试将任一类型的对象转换为泛型类型 T
 * 这个函数使用了 @Suppress("UNCHECKED_CAST") 注解来抑制可能的未检查类型转换警告，不会在运行时进行类型检查，
 * 如果转换不成功，它可能会导致后续代码出现问题，但不会立即抛出异常
 */
@Suppress("UNCHECKED_CAST")
fun <T> Any.saveAsUnchecked(): T {
    return this as T
}

/**
 * 判断任一类型的对象是否为泛型类型 T 的实例
 * 使用了 reified 关键字，因此它在运行时知道 T 的具体类型，并能够安全地进行类型检查
 */
inline fun <reified T> Any.isEqualType(): Boolean {
    return this is T
}