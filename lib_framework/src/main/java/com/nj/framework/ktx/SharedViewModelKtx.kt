package com.nj.framework.ktx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * 在不同的 LifecycleOwner 之间共享 ViewModel 实例，基于指定的作用域（scopeName）
 */

/**
 * 存储不同作用域（scopeName）下的 VMStore 实例（ViewModel）
 */
val vMStores = HashMap<String, VMStore>()


inline fun <reified VM : ViewModel> LifecycleOwner.sharedViewModels(
    scopeName: String,
    factory: ViewModelProvider.Factory? = null
): Lazy<VM> {
    val store: VMStore
    if (vMStores.keys.contains(scopeName)) {
        store = vMStores[scopeName]!!
    } else {
        store = VMStore()
        vMStores[scopeName] = store
    }
    store.register(this)
    return ViewModelLazy(VM::class, { store.viewModelStore }, { factory ?: ViewModelProvider.NewInstanceFactory() })
}


class VMStore : ViewModelStoreOwner {

    private val bindTargets = ArrayList<LifecycleOwner>()
    private var vmStore: ViewModelStore? = null

    fun register(host: LifecycleOwner) {
        if (!bindTargets.contains(host)) {
            bindTargets.add(host)
            host.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(
                    source: LifecycleOwner,
                    event: Lifecycle.Event
                ) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        host.lifecycle.removeObserver(this)
                        bindTargets.remove(host)
                        if (bindTargets.isEmpty()) {
                            vMStores.entries.find { it.value == this@VMStore }?.also {
                                vMStores.clear()
                                vMStores.remove(it.key)
                            }
                        }
                    }
                }
            })
        }
    }

    override fun getViewModelStore(): ViewModelStore {
        if (vmStore == null) vmStore = ViewModelStore()
        return vmStore!!
    }

}