package com.nj.framework.service

import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 */
interface IActivityService {

    fun viewBinding(): ViewBinding

    fun showLoading(loadingMessage: String? = null)

    fun showLoading(@StringRes resId: Int)

    fun hideLoading()

}