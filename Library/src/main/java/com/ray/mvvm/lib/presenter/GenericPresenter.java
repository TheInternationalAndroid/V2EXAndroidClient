/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 22:14:52 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 22:14:52 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.mvvm.lib.presenter;

import android.support.annotation.NonNull;

import com.ray.mvvm.lib.model.http.ErrorType;
import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.http.event.ErrorEvent;
import com.ray.mvvm.lib.model.model.RespEntity;
import com.squareup.leakcanary.RefWatcher;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class GenericPresenter extends CommonPresenter {

    public GenericPresenter(RefWatcher refWatcher) {
        super(refWatcher);
    }

    protected <T> void subscribeGenericReq(@NonNull Observable<RespEntity<T>> observable, @NonNull Subscriber<T> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(subscriber::onStart)
                        .subscribe(subscriber)
        );
    }

    protected <T, R> void subscribeGenericReqConvert(@NonNull Observable<RespEntity<T>> observable, Func1<? super T, ? extends Observable<? extends R>> converter, @NonNull Subscriber<R> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(converter)
                        .doOnSubscribe(subscriber::onStart)
                        .subscribe(subscriber)
        );
    }

    protected <T, R> void subscribeGenericReqConvert(@NonNull Observable<RespEntity<T>> observable, Func1<? super T, ? extends Observable<? extends R>> converter, @NonNull Subscriber<R> subscriber, Action1<T> action1) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(action1)
                        .doOnSubscribe(subscriber::onStart)
                        .concatMap(converter)
                        .subscribe(subscriber)
        );
    }

    protected <T, R> void subscribeGenericReqConvert(@NonNull Observable<RespEntity<T>> observable, Func1<? super T, ? extends Observable<? extends R>> converter) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(converter)
                        .subscribe()
        );
    }

    protected <T, R> void subscribeGenericReqConvert(@NonNull Observable<RespEntity<T>> observable, Func1<? super T, ? extends Observable<? extends R>> converter, @NonNull ExObserver<R> observer) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(converter)
                        .doOnSubscribe(observer::onStart)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeGenericReq(@NonNull Observable<RespEntity<T>> observable, ExObserver<T> observer) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(this::postError)
                        .doOnSubscribe(observer::onStart)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeGenericReq(@NonNull Observable<RespEntity<T>> observable, ExObserver<T> observer, Action1<T> doOnNext) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(observer::onStart)
                        .doOnNext(doOnNext)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeGenericReq(@NonNull Observable<RespEntity<T>> observable, @NonNull Subscriber<T> subscriber, Action1<T> doOnNext) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::genericRespFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(doOnNext)
                        .doOnSubscribe(subscriber::onStart)
                        .subscribe(subscriber)
        );
    }

    protected <T extends RespEntity> void subscribeGenericNoResp(@NonNull Observable<T> observable, @NonNull Subscriber<T> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::respFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(subscriber::onStart)
                        .subscribe(subscriber)
        );
    }

    protected <T extends RespEntity> void subscribeGenericNoResp(@NonNull Observable<T> observable, @NonNull Subscriber<T> subscriber, Action1<T> doOnNext) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(respEntity -> isAlive)
                        .flatMap(this::respFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(doOnNext)
                        .doOnSubscribe(subscriber::onStart)
                        .doOnNext((t) -> Timber.i("doOnNext %s  %d", isAlive, Thread.currentThread().getId()))
                        .subscribe(subscriber)
        );
    }

    private <Y extends RespEntity> Observable<Y> respFlatMap(Y respEntity) {
        return Observable.create((subscriber) -> {
            if (respEntity == null) {
                subscriber.onError(new ErrorEvent(ErrorType.RESP_BODY_EMPTY, "Response entity is empty."));
            } else if (respEntity.getCode() == RespEntity.FAILURE || respEntity.getCode() != RespEntity.SUCCESS) {
                postError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
                subscriber.onError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
            } else {
                subscriber.onNext(respEntity);
            }
        });
    }

    private <Z> Observable<Z> genericRespFlatMap(RespEntity<Z> respEntity) {
        return Observable.create((subscriber) -> {
            if (respEntity == null) {
                subscriber.onError(new ErrorEvent(ErrorType.RESP_BODY_EMPTY, "Response entity is empty."));
            } else if (respEntity.getCode() == RespEntity.FAILURE || respEntity.getCode() != RespEntity.SUCCESS) {
                postError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
                subscriber.onError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
            } else {
                subscriber.onNext(respEntity.getData());
            }
        });
    }

    protected Observable<RespEntity> mockGenericRespObservable() {
        return Observable
                .create(new Observable.OnSubscribe<RespEntity>() {
                    @Override
                    public void call(Subscriber<? super RespEntity> subscriber) {
                        subscriber.onNext(new RespEntity(RespEntity.SUCCESS));
                    }
                })
                .delay(3, TimeUnit.SECONDS);
    }

    protected <T> Observable<RespEntity<T>> mockGenericRespObservable(T t) {
        return Observable
                .create(new Observable.OnSubscribe<RespEntity<T>>() {
                    @Override
                    public void call(Subscriber<? super RespEntity<T>> subscriber) {
                        subscriber.onNext(new RespEntity<>(RespEntity.SUCCESS, t));
                    }
                })
                .delay(3, TimeUnit.SECONDS);
    }
}
