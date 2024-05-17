# SharedPreferences全局访问和读写

## 功能

### 全局应用程序实例

- `application`: 一个全局的 `Application` 实例，便于在应用程序的任何地方访问应用程序上下文。

#### SharedPreferences 访问

- `sharedPreferences`: 一个延迟初始化的 `SharedPreferences` 实例，用于存储和检索应用偏好。

### SharedPreferences 读写辅助函数

- `writeSharedPreferences`: 一个函数用于向 `SharedPreferences` 写入数据。
- `readSharedPreferences`: 一个内联函数用于从 `SharedPreferences` 读取数据。

## 使用方法

#### 初始化全局应用程序实例

在自定义的 `Application` 类的 `onCreate` 方法中初始化 `application` 变量：

```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        com.nj.framework.global.application = this
    }
}
读写 SharedPreferences
// 写入
com.nj.framework.global.writeSharedPreferences {
    putString("username", "JohnDoe")
}

// 读取
val username: String? = com.nj.framework.global.readSharedPreferences<String>("username")
````
## 注意事项
- 确保在 AndroidManifest.xml 中声明并配置了您的自定义应用程序类。
- lateinit 变量 application 必须在使用应用程序的任何其他部分之前初始化。

## 版本历史
v1.0.0
- 初始版本，提供了全局实例和读写SharedPreferences辅助函数。
## 贡献
欢迎提交 issue 和 pull request 来改进此库。

## 许可证
本项目采用 MIT License。