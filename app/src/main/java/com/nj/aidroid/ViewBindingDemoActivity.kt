package com.nj.aidroid

import android.os.Bundle
import com.nj.aidroid.databinding.ActivityViewBindingDemoBinding
import com.nj.framework.basic.BaseActivity

/**
 * Author: liecoder
 * Date: 2024/5/14 周二
 * Version: 1.0
 * Contact: liecoder@163.com
 */
class ViewBindingDemoActivity : BaseActivity<ActivityViewBindingDemoBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding.tvHelloWorld.text = "Hello Aidroid!"
    }

}