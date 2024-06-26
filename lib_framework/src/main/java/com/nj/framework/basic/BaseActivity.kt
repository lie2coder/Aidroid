package com.nj.framework.basic

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.nj.framework.global.saveAs
import com.nj.framework.global.saveAsUnchecked
import com.nj.framework.loading.LoadingDialog
import com.nj.framework.manager.PermissionManager
import com.nj.framework.service.IBasicService
import com.nj.framework.utils.StatusBarUtil
import java.lang.ref.WeakReference
import java.lang.reflect.ParameterizedType

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
    IBasicService {

    protected val mBinding: VB by lazy { viewBinding() }
    protected val mContext: AppCompatActivity by lazy { this }
    protected val mWeakReference: WeakReference<AppCompatActivity> by lazy { WeakReference(mContext) }
    protected val TAG: String by lazy { this.javaClass.simpleName }
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        initialization()
        setupLiveDataObservers()
        setupViewObservers()
        loadData()
        setupStatusBar()
        lifecycle.addObserver(LifecycleEventObserver { _: LifecycleOwner?, event: Lifecycle.Event ->
            Log.e(TAG, "$event")
        })
    }

    final override fun viewBinding(): VB {
        try {
            val type = javaClass.genericSuperclass
            val bindingClass: Class<VB> =
                type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAsUnchecked()
            val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)
            return inflateMethod.invoke(null, layoutInflater)!!.saveAsUnchecked()
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Failed to create ViewBinding for ${this.javaClass.name}", e)
        }
    }

    override fun initialization() {
    }

    override fun setupLiveDataObservers() {
    }

    override fun setupViewObservers() {
    }

    override fun loadData() {
    }

    open fun setupStatusBar() {
        StatusBarUtil.setStatusBar(this, true, Color.WHITE, false)
    }

    final override fun showLoading(loadingMessage: String?) {
        if (isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        loadingDialog.show(loadingMessage)
    }

    final override fun showLoading(@StringRes resId: Int) {
        if (isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        loadingDialog.show(resId)
    }

    final override fun hideLoading() {
        if (isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    /**
     * 处理权限请求结果
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.onRequestPermissionsResult(this, requestCode, permissions, grantResults)
    }

}