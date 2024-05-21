# View 扩展函数文档

以下是一组用于操作 Android `View` 对象的 Kotlin 扩展函数，它们提供了设置圆角、边距、尺寸以及动画等实用功能。

## 设置圆角

### 单一圆角半径

```kotlin
fun View.radius(radiusDP: Int): View {
    // ...函数实现...
}
```

此函数为 View 设置统一的圆角大小。支持链式调用。

#### 参数:

- radiusDP: 圆角半径，以密度无关像素为单位。

### 四角不同圆角半径

```kotlin
@SuppressLint("NewApi")
fun View.radius(
    leftTopRadius: Int = 0,
    rightTopRadius: Int = 0,
    rightBottomRadius: Int = 0,
    leftBottomRadius: Int = 0
): View {
    // ...函数实现...
}
```

此函数为 View 设置不同角的圆角大小。注意，此函数仅在 Android API 31 及以上版本有效。

#### 参数:

- leftTopRadius: 左上角圆角半径。
- rightTopRadius: 右上角圆角半径。
- rightBottomRadius: 右下角圆角半径。
- leftBottomRadius: 左下角圆角半径。

### 设置边距

```kotlin
fun View.margin(
    leftMargin: Int = Int.MAX_VALUE,
    topMargin: Int = Int.MAX_VALUE,
    rightMargin: Int = Int.MAX_VALUE,
    bottomMargin: Int = Int.MAX_VALUE
): View {
    // ...函数实现...
}
```

此函数为 View 设置边距。

#### 参数:

- leftMargin: 左边距。
- topMargin: 上边距。
- rightMargin: 右边距。
- bottomMargin: 下边距。

### 尺寸动画

#### 宽度动画

```kotlin
fun View.animateWidth(
    targetValue: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
) {
// ...函数实现...
}
```

此函数为 View 的宽度变化添加动画效果。

##### 参数:

- targetValue: 目标宽度。
- duration: 动画时长。
- listener: 动画监听器。
- action: 动画过程中的可选行为。

#### 高度动画

```kotlin
fun View.animateHeight(
    targetValue: Int,
    duration: Long = 400,
    listener: Animator.AnimatorListener? = null,
    action: ((Float) -> Unit)? = null
) {
// ...函数实现...
}
```

此函数为 View 的高度变化添加动画效果。

##### 参数:

- targetValue: 目标高度。
- duration: 动画时长。
- listener: 动画监听器。
- action: 动画过程中的可选行为。

### 设置尺寸

#### 宽度

```kotlin
fun View.width(width: Int): View {
// ...函数实现...
}
```

此函数直接设置 View 的宽度。

##### 参数:

- width: 要设置的宽度。

#### 高度

```kotlin
fun View.height(height: Int): View {
// ...函数实现...
}
```

此函数直接设置 View 的高度。

##### 参数:

- height: 要设置的高度。

#### 宽度和高度

```kotlin
fun View.widthAndHeight(
    width: Int,
    height: Int
): View {
// ...函数实现...
}
```

此函数同时设置 View 的宽度和高度。

##### 参数:

- width: 要设置的宽度。
- height: 要设置的高度。

### 尺寸限制

#### 限制高度

```kotlin
fun View.limitHeight(
    h: Int,
    min: Int,
    max: Int
): View {
// ...函数实现...
}
```

此函数设置 View 的高度，并限制在最小和最大高度之间。

##### 参数:

- h: 要设置的高度。
- min: 最小高度。
- max: 最大高度。

#### 限制宽度

```kotlin
fun View.limitWidth(
    w: Int,
    min: Int,
    max: Int
): View {
// ...函数实现...
}
```

此函数设置 View 的宽度，并限制在最小和最大宽度之间。

##### 参数:

- w: 要设置的宽度。
- min: 最小宽度。
- max: 最大宽度。

### 截图

```kotlin
fun View.toBitmap(): Bitmap {
// ...函数实现...
}
```

此函数获取 View 的截图，支持获取整个 RecyclerView 列表的长截图。

- 注意：调用该方法时，请确保 View 已经测量完毕，如果宽高为 0，则将抛出异常。