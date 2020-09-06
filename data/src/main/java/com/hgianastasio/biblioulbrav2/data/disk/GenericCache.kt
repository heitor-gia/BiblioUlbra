package com.hgianastasio.biblioulbrav2.data.disk

import java.io.IOException

/**
 * Created by heitorgianastasio on 4/28/17.
 */
interface GenericCache<T> {
    @Throws(IOException::class)
    fun get(): T

    @Throws(IOException::class)
    fun save(t: T): Boolean
    fun clearCache(): Boolean
    val isUpdated: Boolean
}