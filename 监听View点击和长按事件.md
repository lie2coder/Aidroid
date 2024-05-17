# View 点击扩展函数

## 目录

- [简介](#简介)
- [功能](#功能)
- [使用方法](#使用方法)
- [注意事项](#注意事项)
- [版本历史](#版本历史)
- [贡献](#贡献)
- [许可证](#许可证)

## 简介

该库提供了Android `View` 的点击事件扩展函数，使得处理点击事件变得更加简单和直观。

## 功能

### 长按点击监听器

通过 `onLongClick` 扩展函数，您可以轻松地为任意 `View`
设置长按事件监听器。长按事件的处理逻辑由您提供的 `block` 决定。

### 防抖点击监听器

`onClick` 扩展函数允许您为 `View` 设置点击事件监听器，并提供防抖功能。在指定的等待时间内，即使用户多次点击，也只会执行一次您定义的代码块。

## 使用方法

```kotlin
// 长按点击事件
val myView = findViewById<View>(R.id.my_view)
myView.onLongClick {
    // 长按事件处理逻辑
    Log.d("LongClick", "View was long clicked")
    // 返回 true 表示事件已被消费
    true
}

// 防抖点击事件
val myButton = findViewById<Button>(R.id.my_button)
myButton.onClick(wait = 200) {
    // 点击事件处理逻辑
    Log.d("Click", "Button was clicked")
    // 防抖逻辑会在 200 毫秒内只触发一次
}
```

## 注意事项

- 长按事件和点击事件的处理逻辑应在您提供的 block 中实现。
- 长按事件的返回值应根据实际需求设置，以确保事件正确地传递或消费。

## 版本历史

v1.0.0

- 初始版本，提供长按和点击事件的扩展函数。

## 贡献

欢迎提交 issue 和 pull request 来改进此库。

## 许可证

本项目采用 MIT License。