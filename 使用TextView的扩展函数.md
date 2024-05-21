# Android TextView 扩展函数库

本库提供了一系列 Kotlin 扩展函数，用于在 Android 应用开发中简化 `TextView` 的样式设置和文本操作。

## 功能列表

### getViewWidth

计算并返回 `TextView` 中文本占用的总宽度，包括文本内容的宽度、内边距和外边距。

### strikeThroughText

给 `TextView` 中的文本添加删除线效果。

### cancelStrikeThroughText

取消 `TextView` 中文本的删除线效果。

### bold

使 `TextView` 中的文本呈现为粗体样式（加小粗）。

### Bold

设置 `TextView` 的字体样式为加粗（加大粗）。

## 使用方法

### 获取文本宽度

```kotlin
val textViewWidth = textView.getViewWidth()
```

### 添加删除线效果

```kotlin
textView.strikeThroughText()
```

### 取消删除线效果

```kotlin 
textView.cancelStrikeThroughText()
```

### 设置文本为粗体样式（加小粗）

```kotlin
textView.bold()
```

### 设置字体为加粗样式（加大粗）

```kotlin
textView.Bold()
```

## 注意事项

- getViewWidth 函数返回的宽度值以像素（px）为单位。
- 删除线效果通过 Paint 的 STRIKE_THRU_TEXT_FLAG 标志位实现。
- 粗体样式设置有两种方法：bold 使用 isFakeBoldText 属性为文本添加小粗效果，而 Bold 函数则通过改变
  Typeface 属性为文本设置大写加粗样式。

## 版本历史

v1.0.0

- 初始版本发布，包含 TextView 文本样式设置的扩展函数。