package com.hgianastasio.biblioulbrav2.core.base.boundaries;

import java.io.IOException;

/**
 * Created by heitorgianastasio on 4/24/17.
 */
@FunctionalInterface
public interface OnResultListener<T> {
    void onResult(T result) throws IOException;
}
