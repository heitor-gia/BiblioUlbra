package com.hgianastasio.biblioulbrav2.system

import android.app.Activity
import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.di.PerActivity
import javax.inject.Inject

/**
 * Created by heitor_12 on 24/05/17.
 */
@PerActivity
class UseCaseExecutor @Inject constructor(var activity: Activity) {
    fun <T, P> execute(useCase: UseCase<T, P>, parameter: P, resultListener: OnResultListener<T>, errorListener: OnErrorListener?) {
        useCase.execute(parameter,
                { result: T -> resultToUiThread(result, resultListener, errorListener) },
                { error: Exception -> activity.runOnUiThread { errorListener!!.invoke(error) } }
        )
    }

    fun <T> resultToUiThread(result: T, onResultListener: OnResultListener<T>, errorListener: OnErrorListener?) {
        activity.runOnUiThread {
            try {
                onResultListener(result)
            } catch (e: Exception) {
                errorListener!!.invoke(e)
            }
        }
        activity.runOnUiThread { init() }
    }

    private fun init() {}

}