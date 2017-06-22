package com.hgianastasio.biblioulbrav2.data.network.cloud;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException;
import com.hgianastasio.biblioulbrav2.data.base.user.UserOnCloud;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity;
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity;
import com.hgianastasio.biblioulbrav2.data.network.BiblioUlbraApi;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/28/17.
 */

public class UserOnCloudImpl implements UserOnCloud {
    BiblioUlbraApi api;

    @Inject
    public UserOnCloudImpl(BiblioUlbraApi api) {
        this.api = api;
    }



    @Override
    public void login(String cgu, OnResultListener<UserEntity> listener) throws FailLoginException, ConnectionException {
        try {
            UserEntity result = api.login(cgu).execute().body();

            if( !result.getName().equalsIgnoreCase("falso") ) {
                result.setCgu(cgu);
                listener.onResult(result);
            } else
                throw new FailLoginException(cgu);

        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public void renew(String cgu, OnResultListener<RenewLoansResponseEntity> listener) throws ConnectionException, FailRenewException {
        try {
            RenewLoansResponseEntity result = api.renew(cgu).execute().body();

            if(result.getStatus().equalsIgnoreCase("sim")) {
                listener.onResult(result);
            } else
                throw new FailRenewException(result.getMessage());

        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }
}
