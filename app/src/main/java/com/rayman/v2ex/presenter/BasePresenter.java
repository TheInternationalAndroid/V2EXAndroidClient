/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 3/2/16 11:10 AM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: BasePresenter.
 * Author: Lena; Last Modified: 3/2/16 11:10 AM.
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

package com.rayman.v2ex.presenter;

import android.support.annotation.NonNull;

import com.rayman.v2ex.model.http.ErrorType;
import com.rayman.v2ex.model.http.event.ErrorEvent;
import com.rayman.v2ex.widget.eventbus.RxBus;
import com.rayman.v2ex.widget.eventbus.event.BaseEvent;
import com.squareup.leakcanary.RefWatcher;

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

public class BasePresenter implements IPresenter {

    private CompositeSubscription subscription = new CompositeSubscription();
    private final RefWatcher refWatcher;
    private boolean isAlive;

    public BasePresenter(RefWatcher refWatcher) {
        this.refWatcher = refWatcher;
    }

    @Override
    public void onViewAttach() {
        isAlive = true;
    }

    @Override
    public void onViewDetach() {
        isAlive = false;
        subscription.unsubscribe();
        refWatcher.watch(this);
    }

    @Override
    public void subscribe(Subscription subscription) {
        this.subscription.add(subscription);
    }

    @Override
    public <T extends BaseEvent> void subscribeEvent(Class<T> aClass, Action1<T> eventAction) {
        subscribe(
                RxBus.instance()
                        .asObservable(aClass)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(eventAction)
        );
    }

    protected <T> void subscribeHttpReq(@NonNull Observable<Response<T>> observable, @NonNull Subscriber<T> subscriber) {
        Timber.i("defaultCall %d", Thread.currentThread().getId());
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(subscriber::onStart)
                        .doOnUnsubscribe(this::unSubscribe)
                        .filter(this::httpReqFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::dataFlatMap)
                        .doOnError(this::postError)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    protected <T> void subscribeAsyncRun(@NonNull Observable<T> observable, @NonNull Subscriber<T> subscriber) {
        Timber.i("defaultCall %d", Thread.currentThread().getId());
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(subscriber::onStart)
                        .doOnUnsubscribe(this::unSubscribe)
                        .filter(this::asyncRunFilter)
                        .flatMap(this::dataFlatMap)
                        .doOnError(this::postError)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    private <T> boolean httpReqFilter(Response<T> response) {
        Timber.i("httpReqFilter %s  %d", isAlive, Thread.currentThread().getId());
        return isAlive;
    }

    private <T> boolean asyncRunFilter(T t) {
        Timber.i("asyncRunFilter %s  %d", isAlive, Thread.currentThread().getId());
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

}
