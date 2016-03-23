package com.rayman.v2ex.model.model;

import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;

import auto.parcel.AutoParcel;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/23/16
 * Time: 11:16 AM
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
@AutoParcel
public abstract class TestAutoValueEntity implements Parcelable {

    @JSONCreator
    public static TestAutoValueEntity create(@JSONField(name = "name") String name, @JSONField(name = "phone") String phone, long id, boolean isMine) {
        return AutoParcel_TestAutoValueEntity.create(name, phone, id, isMine);
    }

    public abstract String getName();

    public abstract String getPhone();

    public abstract long getId();

    public abstract boolean isMine();

}
