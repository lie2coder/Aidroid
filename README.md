# Aidroid

欢迎来到 Aidroid，这是一个集合了多种扩展函数、工具类和基类封装的 Android
工具库。来源整理自网络和自己编写，主要用于记录和学习。

## 简介

- **扩展函数**: 一系列 Kotlin 扩展函数，简化 Android 开发中的常见任务。
- **工具类**: 提供了常用的工具类，如日期处理、网络请求、图片加载等。
- **基类封装**: 包括 BaseActivity、BaseFragment 等，简化组件的创建和生命周期管理。

## 集成

### 添加依赖

在你的项目中的 `build.gradle` 文件中添加以下依赖：

```gradle
dependencies {
    
}
```

## 目录

- Basic
    - [BaseActivity](#BaseActivity)
    - [BaseFragment](#BaseFragment)

### BaseActivity

#### IBasicService 接口

IBasicService 定义了以下方法：

- viewBinding(): 返回与当前 Activity 关联的 ViewBinding 对象。
- initialization(): 进行初始化设置。
- setupLiveDataObservers(): 设置 LiveData 观察者。
- setupViewObservers(): 设置视图观察者。
- loadData(): 加载数据。
- showLoading(loadingMessage: String?): 显示带有可选消息的加载对话框。
- showLoading(@StringRes resId: Int): 显示带有资源 ID 指定消息的加载对话框。
- hideLoading(): 隐藏加载对话框。

#### BaseActivity 抽象类

BaseActivity 提供了 IBasicService 的默认实现，并添加了以下功能：

- 通过 mBinding 属性提供延迟初始化的 ViewBinding 实例，自动化了 ViewBinding 的创建过程，简化了布局文件与 Activity 之间的绑定。
- mContext 提供当前 AppCompatActivity 的上下文。
- mWeakReference 为当前 Activity 提供了一个弱引用，避免内存泄漏。
- TAG 为日志输出提供当前类的简单类名。
- loadingDialog 为加载对话框提供管理。
