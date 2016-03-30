package com.rayman.v2ex.model.model.test;

import com.rayman.v2ex.ui.adapter.OnItemClick;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 10:37 AM
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
public class TestRxEntity {

    private String title;
    private OnItemClick<TestRxEntity> onItemClick;

    public TestRxEntity() {
    }

    public TestRxEntity(String title) {
        this.title = title;
    }

    public TestRxEntity(String title, OnItemClick<TestRxEntity> onItemClick) {
        this.title = title;
        this.onItemClick = onItemClick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OnItemClick<TestRxEntity> getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick<TestRxEntity> onItemClick) {
        this.onItemClick = onItemClick;
    }
}
