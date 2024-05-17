# Android SpannableString 扩展函数库

## 简介

`com.nj.framework.ktx` 包提供了一组 Kotlin 扩展函数，旨在简化 Android 开发中对 `TextView` 和 `CharSequence` 的样式操作。

## 功能列表

### 相对大小设置
- `relativeSizeSpan`: 设置文本的相对大小。

### 绝对大小设置
- `absoluteSizeSpan`: 设置文本的绝对大小（像素单位）。

### 文本颜色设置
- `foregroundColorSpan`: 更改文本的前景色。

### 文本背景色设置
- `backgroundColorSpan`: 更改文本的背景色。

### 删除线样式
- `strikethroughSpan`: 为文本添加删除线。

### 可点击样式
- `clickableSpan`: 创建可点击文本，并定义点击动作。

### 文本样式设置
- `styleSpan`: 设置文本样式，如加粗或斜体。

## 使用方法

### 添加依赖

首先，确保项目中已添加库依赖。

### 应用扩展函数

以下是如何使用这些扩展函数的示例：

```kotlin
// 示例：设置文本的相对大小
textView.relativeSizeSpan(
    str = "Hello, World!",
    range = 0 until 5,
    scale = 2.0f
)

// 示例：设置文本的前景色为蓝色
textView.foregroundColorSpan(
    str = "Hello, World!",
    range = 6 until 12,
    color = Color.BLUE
)

// 示例：创建可点击的文本
textView.clickableSpan(
    str = "Click me",
    range = 0 until 5,
    color = Color.RED,
    isUnderlineText = true,
    clickAction = {
        // 点击时执行的代码
    }
)
```
## 注意事项
- 确保 range 参数在文本字符串的有效索引范围内。
- 使用 clickableSpan 时，需要设置 LinkMovementMethod 以支持点击事件。
## 版本历史
v1.0.0
- 初始版本发布，包含基本文本样式设置的扩展函数。
## 贡献
欢迎通过提交 issue 或 pull request 参与贡献。

## 许可证
本项目采用 MIT License。