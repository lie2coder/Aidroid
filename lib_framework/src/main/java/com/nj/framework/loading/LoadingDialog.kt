package com.nj.framework.loading

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.annotation.StringRes
import com.nj.framework.R
import com.nj.framework.databinding.LayoutLoadingDialogBinding

/**
 * Author: liecoder
 * Date: 2024/5/16 周四
 * Version: 1.0
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.loading_dialog) {

    private var mBinding: LayoutLoadingDialogBinding
    private var animation: Animation? = null

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        mBinding = LayoutLoadingDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(mBinding.root)
        initAnimation()
    }

    private fun initAnimation() {
        animation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        animation?.duration = 2000
        animation?.repeatCount = 40
        animation?.fillAfter = true
    }

    fun show(message: String?) {
        mBinding.tvMessage.text = message
        show()
    }

    fun show(@StringRes resId: Int) {
        mBinding.tvMessage.setText(resId)
        show()
    }

    override fun show() {
        super.show()
        mBinding.ivLoading.startAnimation(animation)
    }

    override fun dismiss() {
        super.dismiss()
        mBinding.ivLoading.clearAnimation()
    }

}