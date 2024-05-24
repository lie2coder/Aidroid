package com.nj.aidroid

import android.os.Bundle
import com.nj.aidroid.databinding.ActivityViewBindingDemoBinding
import com.nj.framework.basic.BaseActivity
import com.nj.framework.ktx.onClick
import com.nj.framework.ktx.radius

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 * Contact: liecoder@163.com
 */
class ViewBindingDemoActivity : BaseActivity<ActivityViewBindingDemoBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.tvHelloWorld.radius(leftTopRadius = 8, rightBottomRadius = 8)
        mBinding.tvHelloWorld.filters
    }

}