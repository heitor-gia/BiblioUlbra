package com.hgianastasio.biblioulbrav2.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by heitor_12 on 04/05/17.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
