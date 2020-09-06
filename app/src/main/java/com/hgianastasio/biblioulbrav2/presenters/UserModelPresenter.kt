package com.hgianastasio.biblioulbrav2.presenters

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener
import com.hgianastasio.biblioulbrav2.core.user.interactors.GetUser
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoginUser
import com.hgianastasio.biblioulbrav2.core.user.interactors.LogoutUser
import com.hgianastasio.biblioulbrav2.core.user.models.User
import com.hgianastasio.biblioulbrav2.di.PerActivity
import com.hgianastasio.biblioulbrav2.models.user.UserModel
import com.hgianastasio.biblioulbrav2.models.user.UserModelMapper
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor
import javax.inject.Inject

/**
 * Created by heitor_12 on 09/05/17.
 */
@PerActivity
class UserModelPresenter @Inject constructor(
        executor: UseCaseExecutor,
        private val getUser: GetUser,
        private val logoutUser: LogoutUser,
        var mapper: UserModelMapper,
        private val loginUser: LoginUser) : BasePresenter(executor) {
    fun getUser(onResultListener: OnResultListener<UserModel?>, onErrorListener: OnErrorListener?) {
        useCaseExecutor.execute(getUser, null, { result: User -> onResultListener(mapper.transform(result)) }, onErrorListener)
    }

    fun logout(onResultListener: OnResultListener<Void?>, onErrorListener: OnErrorListener?) {
        useCaseExecutor.execute(logoutUser, null, onResultListener, onErrorListener)
    }

    fun login(cgu: String, onResultListener: OnResultListener<UserModel?>, errorListener: OnErrorListener) {
        progressListener!!.showProgress()
        progressListener!!.hideRetry()
        useCaseExecutor.execute(
                loginUser,
                cgu,
                { result: User ->
                    onResultListener(mapper.transform(result))
                    if (progressListener != null) progressListener!!.hideProgress()
                },
                { e: Exception ->
                    errorListener(e)
                    if (progressListener != null) {
                        progressListener!!.hideProgress()
                        progressListener!!.showRetry()
                    }
                })
    }

}