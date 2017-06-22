package com.hgianastasio.biblioulbrav2.data.disk;

import java.io.IOException;

/**
 * Created by heitorgianastasio on 4/28/17.
 */

public interface GenericCache<T> {
    T get() throws IOException;
    boolean save(T t) throws IOException;
    boolean clearCache();
    boolean isUpdated();
}
