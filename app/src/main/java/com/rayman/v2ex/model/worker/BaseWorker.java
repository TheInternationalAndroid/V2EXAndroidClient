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

package com.rayman.v2ex.model.worker;

import android.support.annotation.NonNull;

import com.rayman.v2ex.model.http.ErrorType;
import com.rayman.v2ex.model.http.event.ErrorEvent;
import com.rayman.v2ex.presenter.ILifeCycle;
import com.rayman.v2ex.widget.eventbus.RxBus;
import com.rayman.v2ex.widget.eventbus.event.BaseEvent;

import java.io.IOException;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

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
public class BaseWorker implements ILifeCycle {

    private boolean isAlive;
    private CompositeSubscription subscription = new CompositeSubscription();

    @Override
    public void onViewAttach() {
        isAlive = true;
    }

    @Override
    public void onViewDetach() {
        isAlive = false;
        subscription.unsubscribe();
    }

    <T> void defaultCall(@NonNull Observable<Response<T>> observable, @NonNull Subscriber<T> subscriber) {
        Timber.i("defaultCall %d", Thread.currentThread().getId());
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(subscriber::onStart)
                        .doOnUnsubscribe(this::unSubscribe)
                        .filter(this::filter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::dataFlatMap)
                        .doOnError(this::postError)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    private boolean filter(Response response) {
        Timber.i("filter %s  %d", isAlive, Thread.currentThread().getId());
        return isAlive;
    }

    private void unSubscribe() {
        Timber.i("unSubscribe %s  %d", isAlive, Thread.currentThread().getId());
    }

    private <T> Observable<T> responseFlatMap(Response<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Timber.i("responseFlatMap %d", Thread.currentThread().getId());
                if (response.isSuccessful()) {
                    subscriber.onNext(response.body());
                } else {
                    subscriber.onError(new Throwable(response.message()));
                }
            }
        });
    }

    private <T> Observable<T> dataFlatMap(T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                Timber.i("dataFlatMap %d", Thread.currentThread().getId());
                if (t == null) {
                    subscriber.onError(new Exception("Response body is empty."));
                } else {
                    subscriber.onNext(t);
                }
            }
        });
    }

    private void postError(Throwable throwable) {
        Timber.i("postError %d %s", Thread.currentThread().getId(), throwable.getLocalizedMessage());
        ErrorEvent errorEvent;
        if (throwable instanceof IOException) {
            errorEvent = new ErrorEvent(ErrorType.ERROR_NETWORK, "网络异常");
        } else {
            errorEvent = new ErrorEvent(ErrorType.ERROR_OTHER, "未知异常");
        }
        RxBus.instance().post(errorEvent);
    }

    public void subscribe(Subscription subscription) {
        this.subscription.add(subscription);
    }

    public <T extends BaseEvent> void subscribeEvent(Class<T> aClass, Action1<T> eventAction) {
        subscribe(
                RxBus.instance()
                        .asObservable(aClass)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(eventAction)
        );
    }

}
