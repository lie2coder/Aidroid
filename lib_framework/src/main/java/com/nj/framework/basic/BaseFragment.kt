package com.nj.framework.basic

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.nj.framework.global.saveAs
import com.nj.framework.global.saveAsUnchecked
import com.nj.framework.loading.LoadingDialog
import com.nj.framework.service.IBasicService
import java.lang.ref.WeakReference
import java.lang.reflect.ParameterizedType

/**
 * Author: liecoder
 * Date: 2024/5/24 周五
 * Version: 1.0
 */
abstract class BaseFragment<VB : ViewBinding> : DialogFragment(), IBasicService {

    protected lateinit var mBinding: VB
    protected lateinit var mContext: FragmentActivity
    protected val mWeakReference: WeakReference<FragmentActivity> by lazy { WeakReference(mContext) }
    protected val TAG: String by lazy { this.javaClass.simpleName }
    private val loadingDialog: LoadingDialog by lazy { LoadingDialog(mContext) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycle.addObserver(LifecycleEventObserver { _: LifecycleOwner?, event: Lifecycle.Event ->
            Log.e(TAG, "$event")
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = viewBinding()
        mContext = requireActivity()
        initialization()
        setupLiveDataObservers()
        setupViewObservers()
        loadData()
        return mBinding.root
    }

    override fun viewBinding(): VB {
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

    final override fun showLoading(loadingMessage: String?) {
        if (mContext.isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        loadingDialog.show(loadingMessage)
    }

    final override fun showLoading(@StringRes resId: Int) {
        if (mContext.isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
        loadingDialog.show(resId)
    }

    final override fun hideLoading() {
        if (mContext.isFinishing) {
            return
        }
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }


}