package com.hgianastasio.biblioulbrav2.system

import android.os.Handler
import android.os.Looper
import com.hgianastasio.biblioulbrav2.core.base.UseCase
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener

/**
 * Created by heitor_12 on 24/05/17.
 */
class UseCaseExecutor constructor() {
    fun <T, P> execute(useCase: UseCase<T, P>, parameter: P, resultListener: OnResultListener<T>, errorListener: OnErrorListener?) {
        useCase.execute(parameter,
                { result: T -> resultToUiThread(result, resultListener, errorListener) },
                { error: Exception -> runOnUiThread { errorListener!!.invoke(error) } }
        )
    }

    fun <T> resultToUiThread(result: T, onResultListener: OnResultListener<T>, errorListener: OnErrorListener?) {
        runOnUiThread {
            try {
                onResultListener(result)
            } catch (e: Exception) {
                errorListener!!.invoke(e)
            }
        }
        runOnUiThread { init() }
    }

    private fun init() {}

    private fun runOnUiThread(runnable:() -> Unit){
        Handler(Looper.getMainLooper()).post(runnable)
    }

}