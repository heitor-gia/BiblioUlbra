package com.hgianastasio.biblioulbrav2.core.base.boundaries;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

@FunctionalInterface
public interface OnErrorListener {
    void onError(Exception e);
}
