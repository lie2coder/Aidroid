# Android ViewModel 共享与管理库

本库提供了一套自定义的机制，用于在 Android 应用中跨组件共享和管理 `ViewModel` 实例。通过使用 `VMStore`
类和 `LifecycleOwner` 的扩展函数，您可以轻松地在不同的生命周期宿主之间共享 `ViewModel`。

## 功能特点

- **作用域共享**: 通过指定的作用域名称，实现 `ViewModel` 的作用域共享。
- **生命周期感知**: 自动管理 `ViewModel` 的生命周期，与 `LifecycleOwner` 同步。
- **延迟初始化**: 使用 `Lazy` 机制，`ViewModel` 的创建延迟至首次访问时。
- **自动清理**: 当所有关联的 `LifecycleOwner` 销毁后，自动清理资源。

### 共享 ViewModel

使用 LifecycleOwner 的 shareViewModels 扩展函数来获取共享的 ViewModel：

```kotlin
val sharedViewModel: MyViewModel by activity.shareViewModels("myScope")
```

### 生命周期管理

VMStore 类自动管理与 LifecycleOwner 的绑定和解绑：

```kotlin
class MyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shareViewModels("myScope") // 在适当的时候注册
    }

    override fun onDestroy() {
        super.onDestroy()
        // 自动解绑和清理
    }

}
```

### 工作原理

#### VMStore 类

VMStore 类实现了 ViewModelStoreOwner 接口，用于存储和管理 ViewModel 实例。它维护了一个 LifecycleOwner
的列表，并在 Lifecycle.Event.ON_DESTROY 事件时进行资源清理。

#### shareViewModels 扩展函数

这是一个内联函数，它根据提供的 scopeName 创建或获取一个 VMStore 实例，并返回一个 Lazy 对象，用于延迟初始化
ViewModel。

### 进阶用法

#### 自定义 ViewModelProvider.Factory

如果您需要自定义 ViewModel 的创建过程，可以提供一个 ViewModelProvider.Factory 实例：

```kotlin
val sharedViewModel: MyViewModel by activity.shareViewModels("myScope", myFactory)
```
