# DP 转换扩展函数

提供了一系列扩展函数，用于将 dp（密度无关像素）单位转换为像素单位（px）。

## 函数列表

### `Int.dp2px()` 和 `Float.dp2px()`

将 `Int` 或 `Float` 类型的 dp 值转换为 `Float` 类型的像素值。

### `Int.dp2pxInt()` 和 `Float.dp2pxInt()`

将 `Int` 或 `Float` 类型的 dp 值转换为 `Int` 类型的像素值。

## 使用方法

```kotlin
val dpInt: Int = 16
val dpFloat: Float = 16.5f
val pixelsFloat: Float = dpInt.dp2px()
val pixelsInt: Int = dpFloat.dp2pxInt()
```   

## 注意事项

- 这些函数使用系统的显示度量进行转换，适用于大多数情况。
- 如果需要针对特定上下文进行转换，请考虑传递相应的 Resources 对象。

## 版本历史

v1.0.0

- 初始版本，提供了 .dp2px() 和 .dp2pxInt() 扩展函数。

## 贡献

欢迎提交 issue 和 pull request 来改进此库。

## 许可证

本项目采用 MIT License。