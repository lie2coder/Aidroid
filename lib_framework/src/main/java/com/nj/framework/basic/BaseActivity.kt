package com.nj.framework.basic

import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.nj.framework.ktx.saveAs
import com.nj.framework.ktx.saveAsUnchecked
import com.nj.framework.loading.LoadingDialog
import com.nj.framework.service.IActivityService
import java.lang.reflect.ParameterizedType

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(),
    IActivityService {

    protected val mBinding: VB by lazy { viewBinding() }
    protected val mContext: AppCompatActivity by lazy { this }
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
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

}