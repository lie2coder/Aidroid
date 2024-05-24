package com.nj.framework.service

import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 */
interface IBasicService {

    fun viewBinding(): ViewBinding

    fun initialization()

    fun setupLiveDataObservers()

    fun setupViewObservers()

    fun loadData()

    fun showLoading(loadingMessage: String? = "加载中")

    fun showLoading(@StringRes resId: Int)

    fun hideLoading()

}