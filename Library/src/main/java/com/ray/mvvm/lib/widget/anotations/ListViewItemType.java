package com.ray.mvvm.lib.widget.anotations;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.ray.mvvm.lib.widget.anotations.ListViewItemType.LOAD_MORE;
import static com.ray.mvvm.lib.widget.anotations.ListViewItemType.LOAD_MORE_ERROR;
import static com.ray.mvvm.lib.widget.anotations.ListViewItemType.NO_MORE;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 17/11/2016
 * Time: 10:59 AM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@IntDef({LOAD_MORE, NO_MORE, LOAD_MORE_ERROR})
public @interface ListViewItemType {
    int LOAD_MORE = 1;
    int NO_MORE = 2;
    int LOAD_MORE_ERROR = 3;
}
