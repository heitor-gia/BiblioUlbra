package com.hgianastasio.biblioulbrav2.system.permissions

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by heitorgianastasio on 5/1/17.
 */
class PermissionsHandler(var context: Activity, var permissions: Array<String>) {
    fun check(grantedListener: OnPermissionGrantedListener, deinedListener: OnPermissionDeinedListener) {
        for (permission in permissions) {
            when (checkSinglePermission(permission)) {
                PackageManager.PERMISSION_GRANTED -> grantedListener.onPermissionGranted(permission)
                PackageManager.PERMISSION_DENIED -> deinedListener.onPermissionDeined(permission)
            }
        }
    }

    private fun checkSinglePermission(permission: String): Int {
        return ContextCompat.checkSelfPermission(context, permission)
    }

    fun requestPermission(permission: String?, deinedListener: OnPermissionDeinedListener) {


        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission!!)) {
            deinedListener.onPermissionDeined(permission)
        } else {
            ActivityCompat.requestPermissions(context, arrayOf<String?>(permission), PERMISSIONCODE)
        }
    }

    companion object {
        private const val PERMISSIONCODE = 885
    }

}