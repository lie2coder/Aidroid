package com.nj.framework.manager

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.util.Log

object ActivityManager {

    private val tasks = mutableListOf<Activity>()

    /**
     * 将Activity加入管理器
     */
    fun push(activity: Activity) {
        tasks.add(activity)
        Log.e("ActivityManager", "push: ${activity::class.simpleName}")
    }

    /**
     * 将Activity从管理器中移除
     */
    fun pop(activity: Activity) {
        tasks.remove(activity)
        Log.e("ActivityManager", "pop: ${activity::class.simpleName}")
    }

    /**
     * 获取位于栈顶的Activity
     */
    fun top(): Activity {
        return tasks.last()
    }

    /**
     * 获取所有Activity
     */
    fun getActivities(): MutableList<Activity> {
        return tasks
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity(block: (() -> Unit)? = null) {
        val iterator = tasks.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            iterator.remove()
            activity.finish()
        }
        block?.invoke()
    }

    /**
     * 结束其他activity
     */
    fun finishOtherActivity(vararg activities: Class<out Activity>) {
        val iterator = tasks.iterator()
        while (iterator.hasNext()) {
            val activity = iterator.next()
            if (!activities.contains(activity::class.java)) {
                iterator.remove()
                activity.finish()
            }
        }
    }

    /**
     * 结束指定activity
     */
    fun finishActivity(vararg activities: Class<out Activity>) {
        val iterator = tasks.iterator()
        while (iterator.hasNext()) {
            val task = iterator.next()
            val activityIterator = activities.iterator()
            while (activityIterator.hasNext()) {
                val activity = activityIterator.next()
                if (task::class.java == activity) {
                    iterator.remove()
                    task.finish()
                    break
                }
            }
        }
    }

    /**
     * activity是否在栈中
     */
    fun isActivityExistsInTask(clazz: Class<out Activity>): Boolean {
        if (clazz != null) {
            for (task in tasks) {
                if (task::class.java == clazz) {
                    return true
                }
            }
        }
        return false
    }

    /**
     * 获取指定Activity
     */
    fun getActivity(clazz: Class<out Activity>): Activity? {
        if (clazz != null) {
            for (task in tasks) {
                if (task::class.java == clazz) {
                    return task
                }
            }
        }
        return null
    }

    /**
     * Activity是否已经销毁
     * @param context
     */
    fun isActivityDestroyed(context: Context): Boolean {
        val activity = findActivity(context)
        return if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                activity.isDestroyed || activity.isFinishing
            } else activity.isFinishing
        } else true
    }


    /**
     * ContextWrapper是context的包装类，AppcompatActivity，service，application实际上都是ContextWrapper的子类
     * AppcompatXXX类的context都会被包装成TintContextWrapper
     * @param context
     */
    private fun findActivity(context: Context): Activity? {
        if (context is Activity) {
            return context
        } else if (context is ContextWrapper) {
            return findActivity(context.baseContext)
        }
        return null
    }


}