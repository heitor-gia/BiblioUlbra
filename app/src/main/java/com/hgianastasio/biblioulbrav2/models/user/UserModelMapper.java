package com.hgianastasio.biblioulbrav2.models.user;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.user.models.User;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/05/17.
 */

public class UserModelMapper extends Mapper<User,UserModel> {

    @Inject public UserModelMapper() {}

    @Override
    public UserModel transform(User input) {
        UserModel output = new UserModel();
        output.setName(formatName(input.getName()));
        output.setCgu(input.getCgu());
        output.setBookings(input.getBookings());
        output.setHistory(input.getHistory());
        output.setLoans(input.getLoans());
        output.setDebt(String.format("%.2f",input.getDebt()));
        output.setOverdue(input.getDebt()<0);
        return output;
    }

    @Override
    public User transformBack(UserModel input) {
        User output = new User();
        output.setName(input.getName());
        output.setCgu(input.getCgu());
        output.setBookings(input.getBookings());
        output.setHistory(input.getHistory());
        output.setLoans(input.getLoans());
        output.setDebt(Double.parseDouble(input.getDebt()));
        return output;    }

    private String formatName(String string){
        String[] words = string.split(" ");
        String result = "";
        for (String word:words) {
            if( word.equalsIgnoreCase("da")||
                word.equalsIgnoreCase("de")||
                word.equalsIgnoreCase("do")||
                word.equalsIgnoreCase("dos")||
                word.equalsIgnoreCase("das")) {
                word = word.toLowerCase();
            }else{
                word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            }
            result = result.concat(" "+word);
        }
        return result.trim();
    }
}
