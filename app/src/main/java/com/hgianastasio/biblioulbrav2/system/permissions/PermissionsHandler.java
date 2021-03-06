package com.hgianastasio.biblioulbrav2.system.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by heitorgianastasio on 5/1/17.
 */

public class PermissionsHandler {
    private static final int PERMISSIONCODE = 885;
    Activity context;
    String[] permissions;

    public PermissionsHandler(Activity context, String[] permissions) {
        this.context = context;
        this.permissions = permissions;
    }

    public void check(OnPermissionGrantedListener grantedListener, OnPermissionDeinedListener deinedListener){
        for (String permission : permissions){
            switch (checkSinglePermission(permission)){
                case PackageManager.PERMISSION_GRANTED:
                    grantedListener.onPermissionGranted(permission);
                    break;
                case PackageManager.PERMISSION_DENIED:
                    deinedListener.onPermissionDeined(permission);
                    break;
            }
        }
    }

    private int checkSinglePermission(String permission){
        return ContextCompat.checkSelfPermission(context,permission);
    }

    public void requestPermission(String permission, OnPermissionDeinedListener deinedListener){


            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(context,permission)) {

                deinedListener.onPermissionDeined(permission);

            } else {

                ActivityCompat.requestPermissions(context, new String[]{permission}, PERMISSIONCODE);

            }

    }
}
