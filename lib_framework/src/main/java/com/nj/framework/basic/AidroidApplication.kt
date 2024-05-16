package com.nj.framework.basic

import android.app.Application
import com.nj.framework.global.application

/**
 * Author: liecoder
 * Date: 2024/5/16 周四
 * Version: 1.0
 */
class AidroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}