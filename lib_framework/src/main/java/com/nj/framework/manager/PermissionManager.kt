package com.nj.framework.manager

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionManager {
    private const val REQUEST_READ_CONTACTS_PERMISSION = 101
    private const val REQUEST_CALL_PHONE_PERMISSION = 103
    private const val REQUEST_PHONE_STATE_PERMISSION = 105
    private const val REQUEST_STORAGE_PERMISSION = 107
    private const val REQUEST_CAMERA_PERMISSION = 109

    private var onGranted: (() -> Unit)? = null
    private var onDenied: ((Int) -> Unit)? = null

    /**
     * 检查并请求相机权限
     */
    fun withCameraPermission(
        activity: Activity,
        block: () -> Unit
    ) {
        withPermission(activity, Manifest.permission.CAMERA, block) {
            //获取权限失败
        }
    }

    /**
     * 检查并请求写内存权限
     */
    fun withStoragePermission(
        activity: Activity,
        block: () -> Unit
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!Environment.isExternalStorageManager()) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                )
                intent.data = Uri.parse("package:" + activity.packageName)
                activity.startActivity(intent)
            } else {
                block.invoke()
            }
        } else {
            withPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, block) {
            }
        }

    }

    /**
     * 检查并请求读取联系人权限
     */
    fun withReadContactsPermission(
        activity: Activity,
        block: () -> Unit
    ) {
        withPermission(activity, Manifest.permission.READ_CONTACTS, block) {
            //获取权限失败
        }
    }

    /**
     * 检查并请求拨打电话权限
     */
    fun withCallPhonePermission(
        activity: Activity,
        block: () -> Unit
    ) {
        withPermission(activity, Manifest.permission.CALL_PHONE, block) {
            //获取权限失败
        }
    }

    /**
     * 检查并请求READ_PHONE_STATE权限
     */
    fun withReadPhoneStatePermission(
        activity: Activity,
        block: () -> Unit
    ) {
        withPermission(activity, Manifest.permission.READ_PHONE_STATE, block) {
            //获取权限失败
        }
    }

    /**
     * 执行需要运行时权限的方法时调用，对运行时权限进行校验和请求
     * 检查是否已经拥有某个权限，如果已经拥有，则执行回调函数；如果没有该权限，则请求该权限
     * [context] 上下文对象，用于获取权限相关的信息
     * [permission] 需要请求的权限
     * [block] 限请求成功后需要执行的回调函数
     * [onDenied] 权限请求被拒绝时需要执行的回调函数，可选参数，默认值为 null
     */
    fun withPermission(
        context: Context,
        permission: String,
        block: () -> Unit,
        onDenied: ((Int) -> Unit)? = null
    ) {
        if (isHavePermission(context, permission)) {
            block.invoke()
        } else {
            requestPermission(context, permission, block, onDenied)
        }
    }

    /**
     * 校验是否拥有运行时权限
     * [context] 上下文
     * [permissionType] 校验的权限类型
     */
    private fun isHavePermission(
        context: Context,
        permissionType: Int
    ): Boolean {
        val permission = when (permissionType) {
            REQUEST_READ_CONTACTS_PERMISSION -> Manifest.permission.READ_CONTACTS
            REQUEST_CALL_PHONE_PERMISSION -> Manifest.permission.CALL_PHONE
            REQUEST_PHONE_STATE_PERMISSION -> Manifest.permission.READ_PHONE_STATE
            REQUEST_STORAGE_PERMISSION -> Manifest.permission.WRITE_EXTERNAL_STORAGE
            REQUEST_CAMERA_PERMISSION -> Manifest.permission.CAMERA
            else -> ""
        }
        return isHavePermission(context, permission)
    }

    /**
     * 校验是否拥有运行时权限
     * [context] 上下文
     * [permission] 校验的权限
     */
    private fun isHavePermission(
        context: Context,
        permission: String
    ): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    /**
     * 请求运行时权限
     *  [context] 上下文
     *  [permission] 请求的权限
     *  [onGranted] 请求授权成功的回调方法
     *  [onDenied] 请示授权失败的回调方法
     */
    private fun requestPermission(
        context: Context,
        permission: String,
        onGranted: () -> Unit,
        onDenied: ((Int) -> Unit)?
    ) {
        this@PermissionManager.onGranted = onGranted
        this@PermissionManager.onDenied = onDenied
        when (permission) {
            Manifest.permission.READ_CONTACTS -> {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    REQUEST_READ_CONTACTS_PERMISSION
                )
            }

            Manifest.permission.CALL_PHONE -> {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CALL_PHONE_PERMISSION
                )
            }

            Manifest.permission.READ_PHONE_STATE -> {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_PHONE_STATE_PERMISSION
                )
            }

            Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_STORAGE_PERMISSION
                )
            }

            Manifest.permission.CAMERA -> {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
            }
        }
    }

    /**
     * 处理权限请求结果
     * [context] 上下文对象，用于获取权限相关的信息
     * [requestCode] 权限请求的请求码，用于标识不同的权限请求
     * [permissions] 请求的权限数组，包含需要请求的权限
     * [grantResults] 授权结果数组，包含对应权限的授权结果
     */
    fun onRequestPermissionsResult(
        context: Context,
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED || isHavePermission(
                context,
                requestCode
            )
        ) {
            // 已授权，或者已经拥有该权限
            onGranted?.invoke()
        } else if (permissions.isNotEmpty() && ActivityCompat.shouldShowRequestPermissionRationale(
                context as Activity,
                permissions[0]
            )
        ) {
            // 如果权限被拒绝，但没有勾选"不再询问"选项
            onDenied?.invoke(2)
        } else {
            // 如果权限被拒绝，并且勾选了"不再询问"选项
            onDenied?.invoke(1)
        }
    }
}