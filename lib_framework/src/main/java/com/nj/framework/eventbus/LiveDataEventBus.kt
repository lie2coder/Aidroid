package com.nj.framework.eventbus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nj.framework.ktx.saveAs
import java.lang.reflect.Field
import java.lang.reflect.Method


object LiveDataEventBus {

    private val _liveDateEventbus: HashMap<String, BusMutableLiveData<Any?>> = hashMapOf()

    private val _liveDataStickyEventBus: HashMap<String, MutableLiveData<Any?>> = hashMapOf()


    /**
     * 创建和获取非粘性事件的 MutableLiveData 实例
     */
    fun <T> with(key: String, type: Class<T>? = null): MutableLiveData<T?> {
        if (!_liveDateEventbus.containsKey(key)) {
            _liveDateEventbus[key] = BusMutableLiveData()
        }
        return _liveDateEventbus[key]!!.saveAs()
    }

    /**
     * 创建和获取粘性事件的 MutableLiveData 实例
     */
    fun <T> withSticky(key: String, type: Class<T>? = null): MutableLiveData<T?> {
        if (!_liveDataStickyEventBus.containsKey(key)) {
            _liveDataStickyEventBus[key] = MutableLiveData()
        }
        return _liveDataStickyEventBus[key]!!.saveAs()
    }

    class ObserverWrapper<T>(private val observer: Observer<in T>?) : Observer<T> {

        override fun onChanged(t: T) {
            if (observer != null) {
                if (isCallObserve()) {
                    return
                }
                observer.onChanged(t)
            }
        }

        private fun isCallObserve(): Boolean {
            val stackTrace = Thread.currentThread().stackTrace
            if (stackTrace.isNotEmpty()) {
                for (element in stackTrace) {
                    if (element.className == "androidx.lifecycle.LiveData" && element.methodName == "observeForever") return true
                }
            }
            return false
        }

    }

    class BusMutableLiveData<T> : MutableLiveData<T>() {

        private val observerMap: HashMap<Observer<in T>, Observer<in T>> = hashMapOf()

        override fun observe(
            owner: LifecycleOwner,
            observer: Observer<in T>
        ) {
            super.observe(owner, observer)
            hook(observer)
        }

        override fun observeForever(observer: Observer<in T>) {
            if (!observerMap.containsKey(observer)) {
                observerMap[observer] = ObserverWrapper(observer)
            }
            super.observeForever(observer)
        }

        override fun removeObserver(observer: Observer<in T>) {
            val realObserver: Observer<in T> = if (observerMap.containsKey(observer)) {
                observerMap.remove(observer)?.saveAs()!!
            } else {
                observer
            }
            super.removeObserver(realObserver)
        }

        /**
         * 通过反射修改LiveData的观察者对象（Observer）的版本号（version）和 LiveData 一致
         * 对于 LiveData，其初始的 version是-1，当我们调用了其 setValue 或者 postValue ，其 vesion 会+1；
         * 对于每一个观察者的封装 ObserverWrapper，其初始 version 也为-1，每一个新注册的观察者，其version 为-1；
         * 当LiveData设置这个 ObserverWrapper 的时候，
         * 如果 LiveData 的 version 大于 ObserverWrapper的version，
         * LiveData 就会强制把当前 value 推送给 Observer
         * 如果不希望订阅者收到订阅之前发布的消息，
         * 需要在注册一个新的订阅者的时候把 Wrapper 的 version 设置成跟 LiveData 的 version 一致
         * 在调用 observe 的时候，通过反射拿到LifecycleBoundObserver，
         * 再把 LifecycleBoundObserver 的 version 设置成和 LiveData 一致即可
         */
        private fun hook(observer: Observer<in T>) {
            try {
                // 获取LiveData的Class对象
                val classLiveData = LiveData::class.java
                // 获取LiveData内部的 mObservers 字段，该字段存储了LiveData的观察者对象
                val fieldObservers = classLiveData.getDeclaredField("mObservers")
                // 将 fieldObservers 的访问权限设置为可访问，以便后续操作
                fieldObservers.isAccessible = true
                // 获取LiveData的观察者对象，将其保存在 objectObservers 中
                val objectObservers: Any? = fieldObservers.get(this)?.saveAs()
                // 获取 objectObservers 的Class对象，保存在 classObservers 中
                val classObservers: Class<Any>? = objectObservers?.javaClass
                // 为了获取LiveData的观察者对象，先获取 get() 方法
                val methodGet: Method? = classObservers?.getDeclaredMethod("get", Any::class.java)
                // 将 methodGet 的访问权限设置为可访问
                methodGet?.isAccessible = true
                // 调用 get() 方法，传入 observer 作为参数，获取LiveData的观察者对象，并将其保存在 objectWrapperEntry 中
                val objectWrapperEntry: Any? = methodGet?.invoke(objectObservers, observer)
                var objectWrapper: Any? = null
                // 如果 objectWrapperEntry 是 Map.Entry 类型的实例，将其中的值保存在 objectWrapper 中
                if (objectWrapperEntry is Map.Entry<*, *>) {
                    objectWrapper = objectWrapperEntry.value
                }
                // 获取 objectWrapper 的父类Class对象，保存在 classObserverWrapper 中
                val classObserverWrapper: Class<in Any>? = objectWrapper?.javaClass?.superclass
                // 获取 classObserverWrapper 的 mLastVersion 字段，该字段存储了LiveData观察者对象的版本号
                val fieldLastVersion: Field? =
                    classObserverWrapper?.getDeclaredField("mLastVersion")
                // 将 fieldLastVersion 的访问权限设置为可访问
                fieldLastVersion?.isAccessible = true
                val fieldVersion: Field = classLiveData.getDeclaredField("mVersion")
                fieldVersion.isAccessible = true
                // 获取LiveData的版本号，保存在 objectVersion 中
                val objectVersion: Any = fieldVersion.get(this)!!.saveAs()
                // 将LiveData的版本号设置给观察者对象的 mLastVersion 字段
                fieldLastVersion?.set(objectWrapper, objectVersion)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}