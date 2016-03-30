package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.presenter.ILifeCycle;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 2:24 PM
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
public interface ITestRxP extends ILifeCycle {

    void testThread();

    void testEmpty();

    void testNever();

    void testFrom();

    void testFromFuture();

    void testInterval();

    void testJust();

    void testRange();

    void testRepeat();

    void testStartWith();
}
