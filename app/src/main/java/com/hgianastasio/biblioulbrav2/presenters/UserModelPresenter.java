package com.hgianastasio.biblioulbrav2.presenters;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnErrorListener;
import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.user.interactors.GetUser;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LoginUser;
import com.hgianastasio.biblioulbrav2.core.user.interactors.LogoutUser;
import com.hgianastasio.biblioulbrav2.di.PerActivity;
import com.hgianastasio.biblioulbrav2.models.user.UserModel;
import com.hgianastasio.biblioulbrav2.models.user.UserModelMapper;
import com.hgianastasio.biblioulbrav2.system.UseCaseExecutor;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */
@PerActivity
public class UserModelPresenter extends BasePresenter {

    private GetUser getUser;
    private LogoutUser logoutUser;
    private LoginUser loginUser;

    UserModelMapper mapper;

    @Inject
    public UserModelPresenter(
            UseCaseExecutor executor,
            GetUser getUser,
            LogoutUser logoutUser,
            UserModelMapper mapper,
            LoginUser loginUser) {
        super(executor);
        this.getUser = getUser;
        this.logoutUser = logoutUser;
        this.mapper = mapper;
        this.loginUser = loginUser;
    }


    public void getUser(OnResultListener<UserModel> onResultListener, OnErrorListener onErrorListener){
        useCaseExecutor.execute(getUser,null,result -> onResultListener.onResult(mapper.transform(result)),onErrorListener);
    }

    public void logout(OnResultListener<Void> onResultListener, OnErrorListener onErrorListener){
        useCaseExecutor.execute(logoutUser,null,onResultListener,onErrorListener);
    }

    public void login(String cgu, OnResultListener<UserModel> onResultListener, OnErrorListener errorListener){
        progressListener.showProgress();
        progressListener.hideRetry();
        useCaseExecutor.execute(
                loginUser,
                cgu,
                result ->{
                    onResultListener.onResult(mapper.transform(result));
                    if(progressListener!=null)progressListener.hideProgress();
                },
                e -> {
                    errorListener.onError(e);
                    if(progressListener!=null){
                        progressListener.hideProgress();
                        progressListener.showRetry();
                    }
                });
    }
}
