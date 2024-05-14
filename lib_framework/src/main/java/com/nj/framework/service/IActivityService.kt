package com.nj.framework.service

import androidx.viewbinding.ViewBinding

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 */
interface IActivityService {

    fun viewBinding(): ViewBinding

    fun showLoading(loadingMessage: String?)

    fun hideLoading()

}