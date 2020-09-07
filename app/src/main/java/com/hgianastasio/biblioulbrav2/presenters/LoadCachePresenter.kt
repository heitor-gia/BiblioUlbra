package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.core.user.interactors.LoadCache
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
/**
 * Created by heitor_12 on 13/05/17.
 */
class LoadCachePresenter constructor(executor: UseCaseExecutor, var loadCache: LoadCache) : BasePresenter(executor) {
    fun loadCache() {
        if (progressListener != null) progressListener!!.showProgress()
        useCaseExecutor.execute(loadCache, null, { if (progressListener != null) progressListener!!.hideProgress() }, null)
    }

}