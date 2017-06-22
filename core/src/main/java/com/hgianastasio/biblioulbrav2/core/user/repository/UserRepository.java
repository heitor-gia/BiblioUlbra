package com.hgianastasio.biblioulbrav2.core.user.repository;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.OnResultListener;
import com.hgianastasio.biblioulbrav2.core.base.exceptions.ConnectionException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailLoginException;
import com.hgianastasio.biblioulbrav2.core.user.exceptions.FailRenewException;
import com.hgianastasio.biblioulbrav2.core.user.models.RenewLoansResponse;
import com.hgianastasio.biblioulbrav2.core.user.models.User;

import java.io.IOException;

/**
 * Created by heitorgianastasio on 4/24/17.
 */

public interface UserRepository {
    void login(String cgu, OnResultListener<User> onResultListener) throws FailLoginException, ConnectionException;
    void logout();
    boolean save(User user) throws IOException;
    void get(OnResultListener<User> onResultListener);
    void renewLoans(String cgu, OnResultListener<RenewLoansResponse> onResultListener) throws FailRenewException, ConnectionException;
}
