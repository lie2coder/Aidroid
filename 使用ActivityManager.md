# Activity 管理器

`ActivityManager` 是一个 Kotlin 对象，提供了一套管理 Android `Activity`
生命周期和栈的方法。它允许您跟踪、结束以及查询 `Activity`。

## 功能

- **Activity 栈管理**：将 `Activity` 添加到栈中，从栈中移除，以及获取栈顶 `Activity`。
- **批量结束 Activity**：结束所有 `Activity` 或者结束指定的多个 `Activity`。
- **检查 Activity**：检查 `Activity` 是否存在，是否销毁，以及获取特定 `Activity`。
- **辅助函数**：`findActivity` 用于从 `Context` 中查找 `Activity`。

## 函数概览

### push

将指定的 Activity 添加到管理器的栈中。

```kotlin
fun push(activity: Activity)
```

### pop

将指定的 Activity 从管理器的栈中移除。

```kotlin
fun pop(activity: Activity)
```

### top

获取位于栈顶的 Activity。

```kotlin
fun top(): Activity
```

### getActivities

获取所有管理中的 Activity。

```kotlin
fun getActivities(): MutableList<Activity>
```

### finishAllActivity

结束所有管理中的 Activity。

```kotlin
fun finishAllActivity(block: (() -> Unit)? = null)
```

### finishOtherActivity

结束除了指定数组中的 Activity 类之外的所有 Activity。

```kotlin
fun finishOtherActivity(vararg activities: Class<out Activity>)
```

### finishActivity

结束指定的 Activity。

```kotlin
fun finishActivity(vararg activities: Class<out Activity>)
```

### isActivityExistsInTask

检查指定的 Activity 是否存在于管理器的栈中。

```kotlin
fun isActivityExistsInTask(clazz: Class<out Activity>): Boolean
```

### getActivity

获取特定类名的 Activity。

```kotlin
fun getActivity(clazz: Class<out Activity>): Activity?
```

### isActivityDestroyed

检查从 Context 中找到的 Activity 是否已经被销毁。

```kotlin
fun isActivityDestroyed(context: Context): Boolean
```

### findActivity

从 Context 中查找 Activity。

```kotlin
private fun findActivity(context: Context): Activity?
```

## 使用方法

### 添加和移除 Activity

```kotlin
ActivityManager.push(this) // 当前 Activity
ActivityManager.pop(this) // 当前 Activity
```

### 结束所有 Activity

```kotlin
ActivityManager.finishAllActivity {
    // 所有 Activity 结束后的回调
}
```

### 检查 Activity 是否存在

```kotlin
val exists = ActivityManager.isActivityExistsInTask(MyActivity::class.java)
```

## 注意事项

- 确保在 Activity 的生命周期事件中正确调用 push 和 pop 方法，以维护 Activity 栈的状态。
- 该管理器仅为辅助工具，实际的 Activity 生命周期管理仍需遵循 Android 的官方指南。

## 版本历史

v1.0.0

- 初始版本，提供了 Activity 管理的一系列方法。