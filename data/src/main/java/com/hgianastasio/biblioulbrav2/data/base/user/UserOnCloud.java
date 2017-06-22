package com.hgianastasio.biblioulbrav2.data.base.user;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException;
import com.hgianastasio.biblioulbrav2.data.models.user.UserEntity;
import com.hgianastasio.biblioulbrav2.data.models.user.renew.RenewLoansResponseEntity;

/**
 * Created by heitorgianastasio on 4/28/17.
 */

public interface UserOnCloud {
    void login(String cgu, OnResultListener<UserEntity> listener) throws FailLoginException, ConnectionException;
    void renew(String cgu, OnResultListener<RenewLoansResponseEntity> listener) throws ConnectionException, FailRenewException;
}
