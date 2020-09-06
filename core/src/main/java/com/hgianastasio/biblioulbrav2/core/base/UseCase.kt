package com.hgianastasio.biblioulbrav2.core.base

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import java.util.concurrent.ThreadPoolExecutor

/**
 * Created by heitorgianastasio on 4/24/17.
 */
abstract class UseCase<T, P> protected constructor(protected var executor: ThreadPoolExecutor) {
    protected abstract fun process(params: P, resultListener: OnResultListener<T>, errorListener: OnErrorListener)
    fun execute(params: P, resultListener: OnResultListener<T>, errorListener: OnErrorListener) {
        executor.execute { process(params, resultListener, errorListener) }
    }

}