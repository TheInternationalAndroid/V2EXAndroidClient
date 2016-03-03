/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/18/16 10:01 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BaseWorker.
 * Author: Lena; Last Modified: 1/18/16 10:01 PM.
 * This file is originally created by Lena.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rayman.v2ex.worker;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rayman.v2ex.http.callback.ReqCallback;
import com.rayman.v2ex.presenter.IPage;

import retrofit.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/18/16
 * Time: 22:01
 * \ ___________________
 * \| Happy New Year!  |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class BaseWorker implements IPage {

    private boolean isAlive;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override public void onViewAttach() {
        isAlive = true;
    }

    @Override public void onViewDetach() {
        isAlive = false;
        subscription.unsubscribe();
    }

    public boolean isAlive() {
        return isAlive;
    }

    private <T> WorkerCallback<T> defaultCallback(ReqCallback<T> callback) {
        return new WorkerCallback<>(this, callback);
    }

    <T> void defaultCall(@NonNull Observable<Response<T>> observable, @Nullable ReqCallback<T> callback) {
        subscription.add(observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(defaultCallback(callback)));
    }

}
