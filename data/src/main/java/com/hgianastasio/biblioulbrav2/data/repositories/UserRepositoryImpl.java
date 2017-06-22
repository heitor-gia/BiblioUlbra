package com.hgianastasio.biblioulbrav2.data.repositories;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.settings.Setting;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException;
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse;
import com.hgianastasio.biblioulbrav2.core.user.models.User;
import com.hgianastasio.biblioulbrav2.core.user.repository.UserRepository;
import com.hgianastasio.biblioulbrav2.data.base.historybooks.HistoryBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.loanbooks.LoanBooksOnCache;
import com.hgianastasio.biblioulbrav2.data.base.setting.SettingOnDisk;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCache;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCloud;
import com.hgianastasio.biblioulbrav2.data.exceptions.UserNotCachedException;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntityMapper;
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity;
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntityMapper;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/26/17.
 */

public class UserRepositoryImpl implements UserRepository {


    private UserOnCache cache;
    private UserOnCloud cloud;
    private UserEntityMapper userMapper;
    private RenewLoansResponseEntityMapper renewMapper;
    private LoanBooksOnCache loanBooksOnCache;
    private HistoryBooksOnCache historyBooksOnCache;
    private SettingOnDisk settingOnDisk;

    @Inject
    public UserRepositoryImpl(
            UserOnCache cache,
            UserOnCloud cloud,
            LoanBooksOnCache loanBooksOnCache,
            HistoryBooksOnCache historyBooksOnCache,
            UserEntityMapper userMapper,
            RenewLoansResponseEntityMapper renewMapper,
            SettingOnDisk settingOnDisk) {
        this.cloud = cloud;
        this.cache = cache;
        this.userMapper = userMapper;
        this.renewMapper = renewMapper;
        this.loanBooksOnCache = loanBooksOnCache;
        this.historyBooksOnCache = historyBooksOnCache;
        this.settingOnDisk = settingOnDisk;
    }

    @Override
    public void login(String cgu, OnResultListener<User> onResultListener) throws FailLoginException,ConnectionException {
        cloud.login(cgu,(u)-> {
            u.setCgu(cgu);
            onResultListener.onResult(userMapper.transformBack(u));
        });
    }

    @Override
    public void logout() {
        cache.clearCache();
        loanBooksOnCache.clearCache();
        historyBooksOnCache.clearCache();
        settingOnDisk.clear();
    }

    @Override
    public boolean save(User user) throws IOException {
        return cache.save(userMapper.transform(user));
    }

    @Override
    public void get(OnResultListener<User> onResultListener) {
        try {
            if (cache.isUpdated())
                onResultListener.onResult(userMapper.transformBack(cache.get()));
            else
                cloud.login(cache.get().getCgu(),result -> onResultListener.onResult(userMapper.transformBack(result)));
        } catch (Exception e) {
            throw new UserNotCachedException(e);
        }
    }

    @Override
    public void renewLoans(String cgu, OnResultListener<RenewLoansResponse> onResultListener) throws FailRenewException, ConnectionException {
        cloud.renew(cgu,(response)->{
            onResultListener.onResult(renewMapper.transformBack(response));
        });
    }
}
