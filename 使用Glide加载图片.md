# Glide 扩展函数库

该库提供了一系列扩展函数，用于简化在 Android `ImageView` 中加载和显示图片的过程。通过封装 Glide
库的功能，这些扩展函数使得图片加载变得更加简洁和易于管理。

## 功能

- **多种资源类型加载**：支持网络 URL、本地资源 ID、Bitmap 和 Drawable 类型的图片加载。
- **圆形和圆角图片**：提供将图片裁剪为圆形或添加圆角效果的功能。
- **带边框的圆形图片**：允许为圆形图片添加边框。
- **内存和磁盘缓存策略**：允许开发者自定义内存和磁盘缓存的行为。

## 扩展函数概览

### load 系列函数

用于加载不同类型的图片资源，并支持自定义内存缓存跳过、占位符、错误图片和加载优先级。

### loadCircular 系列函数

用于加载图片并将其裁剪为圆形，同样支持自定义加载选项。

### loadRounded 系列函数

用于加载图片并为其添加圆角效果，可以指定圆角半径。

### loadCircularBorder 系列函数

用于加载图片，将其裁剪为带边框的圆形，并允许自定义边框宽度和颜色。

## 使用方法

### 基本图片加载

```kotlin
imageView.load(
    url = "https://example.com/image.jpg",
    skipMemoryCache = true,
    placeholder = R.drawable.icon_placeholder,
    error = R.drawable.icon_error,
    priority = Priority.HIGH
)
```

### 加载圆形图片

```kotlin
imageView.loadCircular(
    url = "https://example.com/image.jpg",
    skipMemoryCache = true,
    placeholder = R.drawable.icon_placeholder,
    error = R.drawable.icon_error,
    priority = Priority.HIGH
)
```

### 加载带边框的圆形图片

```kotlin
imageView.loadCircularBorder(
    url = "https://example.com/image.jpg",
    borderWidth = 2f,
    borderColor = Color.WHITE,
    skipMemoryCache = true,
    placeholder = R.drawable.icon_placeholder,
    error = R.drawable.icon_error,
    priority = Priority.HIGH
)
```

## 注意事项

- 确保在使用这些扩展函数之前，您的项目中已经添加了 Glide 库的依赖。
- 这些函数中的 R.drawable.icon_placeholder 和 R.drawable.icon_error 需要替换为您项目中实际存在的资源
  ID。
- ActivityManager.isActivityDestroyed(context) 用于检查 Activity 是否已经被销毁，以避免在 Activity
  销毁后加载图片。

## 版本历史

v1.0.0

- 初始版本，提供了加载图片、圆形图片、圆角图片和带边框圆形图片的扩展函数。