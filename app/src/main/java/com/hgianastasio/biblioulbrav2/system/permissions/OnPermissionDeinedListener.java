package com.hgianastasio.biblioulbrav2.system.permissions;

/**
 * Created by heitorgianastasio on 5/1/17.
 */
@FunctionalInterface
public interface OnPermissionDeinedListener {
    void onPermissionDeined(String permission);
}
