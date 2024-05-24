# LiveDataEventBus

`LiveDataEventBus` 是一个基于 `LiveData` 的事件总线系统，用于在不同组件之间进行通信。它提供了一种发布-订阅模式，允许组件以一种解耦的方式响应事件。

## 特点

- **类型安全**: 通过泛型支持，确保事件类型正确。
- **粘性事件**: 支持粘性事件，允许新订阅者接收到最近一次的事件数据。
- **生命周期感知**: 与 Android 生命周期紧密集成，避免内存泄漏。
- **线程安全**: 可以在任何线程发送事件，主线程接收更新。

## 使用方法

### 发送事件

```kotlin
// 获取非粘性事件的 LiveData 实例
val liveData: MutableLiveData<Event> = LiveDataEventBus.with("EVENT_KEY")

// 发送事件
liveData.postValue(event)
```

### 接收事件

```kotlin
// 获取 LiveData 实例
val liveData: LiveData<Event> = LiveDataEventBus.with("EVENT_KEY")

// 观察 LiveData
lifecycleScope.launchWhenStarted {
    liveData.observe(viewLifecycleOwner, Observer { event ->
        // 处理事件
    })
}
```

### 粘性事件

```kotlin

// 获取粘性事件的 LiveData 实例
val liveData: LiveData<Event> = LiveDataEventBus.withSticky("STICKY_EVENT_KEY")

// 观察 LiveData
lifecycleScope.launchWhenStarted {
    liveData.observe(viewLifecycleOwner, Observer { event ->
        // 即使在注册之前发生的事件，也能接收到
    })
}
```

## 原理
LiveDataEventBus 使用 HashMap 存储事件键和对应的 LiveData 实例。它提供了两个主要函数 with 和
withSticky 来获取或创建 LiveData 实例。

- 非粘性事件: 使用 BusMutableLiveData，一个继承自 MutableLiveData 的类，用于处理非粘性事件。
- 粘性事件: 使用标准的 MutableLiveData，它会自动发送最后一次的事件值给新注册的观察者。
## 扩展功能
LiveDataEventBus 还提供了 ObserverWrapper 类，用于包装原始的 Observer。这允许事件总线系统在必要时进行额外的逻辑处理，例如防止
observeForever 的潜在问题。

## 注意事项
- 确保正确管理观察者的生命周期，避免内存泄漏。
- 使用 withSticky 时，注意粘性事件可能会对新订阅者立即发送，即使它们在事件实际发生后注册。
- 考虑线程安全，LiveDataEventBus 可以在任何线程发送事件，但更新总是在主线程进行。