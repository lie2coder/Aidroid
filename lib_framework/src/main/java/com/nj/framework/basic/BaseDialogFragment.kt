package com.nj.framework.basic

import android.app.Dialog
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Author: liecoder
 * Date: 2024/5/24 周五
 * Version: 1.0
 */
open class BaseDialogFragment : AppCompatDialogFragment() {
    private var mDialog: BaseDialog? = null

    companion object {
        private var sShowTag: String? = null
        private var sLastTime: Long = 0
    }

    /**
     * 父类同名方法简化
     */
    fun show(fragment: Fragment?) {
        if (fragment != null && fragment.activity != null
            && !(fragment.requireActivity().isFinishing) && fragment.isAdded
        ) {
            show(fragment.parentFragmentManager, fragment.javaClass.name)
        }
    }

    /**
     * 父类同名方法简化
     */
    fun show(activity: FragmentActivity?) {
        if (activity != null && !activity.isFinishing) {
            show(activity.supportFragmentManager, activity.javaClass.name)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (manager != null && !manager.isDestroyed) {
            if (!isRepeatedShow(tag)) {
                try {
                    super.show(manager, tag)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun show(transaction: FragmentTransaction, tag: String?): Int {
        if (transaction == null) {
            return -1
        }
        if (!isRepeatedShow(tag)) {
            try {
                return super.show(transaction, tag)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return -1
    }

    /**
     * 根据 tag 判断这个 Dialog 是否重复显示了
     *
     * @param tag Tag标记
     */
    protected fun isRepeatedShow(tag: String?): Boolean {
        val result = tag == sShowTag && SystemClock.uptimeMillis() - sLastTime < 500
        sShowTag = tag
        sLastTime = SystemClock.uptimeMillis()
        return result
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return if (mDialog != null) {
            mDialog!!
        } else {
            // 不使用 Dialog，替换成 BaseDialog 对象
            BaseDialog((activity as? AppCompatActivity)!!).also { mDialog = it }
        }
    }

    override fun getDialog(): Dialog? {
        return if (mDialog != null) {
            mDialog
        } else super.getDialog()
    }

    fun setDialog(dialog: BaseDialog?) {
        mDialog = dialog
    }


    open class Builder<B : BaseDialog.Builder<B>>(
        /**
         * 获取当前 Activity 对象（仅供子类调用）
         */
        protected val activity: FragmentActivity
    ) : BaseDialog.Builder<B>(activity) {

        /**
         * 获取当前 DialogFragment 对象（仅供子类调用）
         */
        protected var dialogFragment: BaseDialogFragment? = null
            private set

        /**
         * 获取 Fragment 的标记
         */
        protected val fragmentTag: String
            protected get() = javaClass.name


        override fun show(): BaseDialog {
            val dialog = create()
            try {
                dialogFragment = initDialogFragment()
                dialogFragment?.setDialog(dialog)
                if (activity != null && !activity.isFinishing) {
                    dialogFragment?.show(activity.supportFragmentManager, fragmentTag)
                }
                // 解决 Dialog 设置了而 DialogFragment 没有生效的问题
                dialogFragment?.isCancelable = isCancelable
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return dialog
        }

        protected fun initDialogFragment(): BaseDialogFragment {
            return BaseDialogFragment()
        }
    }
}