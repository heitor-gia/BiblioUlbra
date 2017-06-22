package com.hgianastasio.biblioulbrav2.data.models.user;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.user.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by heitorgianastasio on 4/26/17.
 */
@Singleton
public class UserEntityMapper extends Mapper<User,UserEntity> {
    @Inject public UserEntityMapper() {}

    @Override
    public User transformBack(UserEntity input) {
        User output = new User();
        output.setName(input.getName());
        output.setCgu(input.getCgu());
        output.setBookings(Integer.parseInt(input.getBookings()));
        output.setHistory(Integer.parseInt(input.getHistory()));
        output.setLoans(Integer.parseInt(input.getLoans()));
        output.setDebt(Double.parseDouble(input.getDebt()));
        return output;
    }

    @Override
    public UserEntity transform(User input) {
        UserEntity output = new UserEntity();
        output.setName(input.getName());
        output.setCgu(input.getCgu());
        output.setBookings(String.valueOf(input.getBookings()));
        output.setHistory(String.valueOf(input.getHistory()));
        output.setLoans(String.valueOf(input.getLoans()));
        output.setDebt(String.valueOf(input.getDebt()));
        return output;    }
}
