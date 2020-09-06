package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
import com.hgianastasio.biblioulbrav2.views.listeners.OnProgressListener

/**
 * Created by heitor_12 on 04/05/17.
 */
abstract class BasePresenter(protected var useCaseExecutor: UseCaseExecutor) {
    var progressListener: OnProgressListener? = null

    fun unsetProgressListener() {
        progressListener = null
    }

    open fun destroy() {}

}