package com.hgianastasio.biblioulbrav2.navigation;

import android.content.Context;
import android.content.Intent;

import com.hgianastasio.biblioulbrav2.views.activities.LoginActivity;
import com.hgianastasio.biblioulbrav2.views.activities.MainActivity;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class Navigator {
    public static void toMainActivity(Context context){
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void toLoginActivity(Context context){
        context.startActivity(new Intent(context, LoginActivity.class));
    }


}
