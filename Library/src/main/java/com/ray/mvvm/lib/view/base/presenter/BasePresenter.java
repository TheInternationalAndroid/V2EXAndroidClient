/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 23:47:37 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 23:47:37 +0800.
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

package com.ray.mvvm.lib.view.base.presenter;

import android.support.annotation.NonNull;

import com.ray.mvvm.lib.model.http.ErrorType;
import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.http.event.ErrorEvent;
import com.ray.mvvm.lib.model.model.RespEntity;
import com.ray.mvvm.lib.widget.eventbus.RxBus;
import com.ray.mvvm.lib.widget.eventbus.event.BaseEvent;
import com.squareup.leakcanary.RefWatcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
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

    protected <T> void subscribeReq(@NonNull Observable<Response<RespEntity<T>>> observable, @NonNull Subscriber<T> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    protected <T, R> void subscribeReqConvert(@NonNull Observable<Response<RespEntity<T>>> observable, Func1<? super T, ? extends Observable<? extends R>> converter, @NonNull Subscriber<R> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(converter)
                        .subscribe(subscriber)
        );
    }

    protected <T, R> void subscribeReqConvert(@NonNull Observable<Response<RespEntity<T>>> observable, Func1<? super T, ? extends Observable<? extends R>> converter, @NonNull Subscriber<R> subscriber, Action1<T> action1) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(action1)
                        .concatMap(converter)
                        .subscribe(subscriber)
        );
    }

    protected <T, R> void subscribeReqConvert(@NonNull Observable<Response<RespEntity<T>>> observable, Func1<? super T, ? extends Observable<? extends R>> converter) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(converter)
                        .subscribe()
        );
    }

    protected <T, R> void subscribeReqConvert(@NonNull Observable<Response<RespEntity<T>>> observable, Func1<? super T, ? extends Observable<? extends R>> converter, @NonNull ExObserver<R> observer) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .concatMap(converter)
                        .doOnSubscribe(observer::onStart)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeCommonReq(@NonNull Observable<Response<T>> observable, @NonNull Subscriber<T> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    protected <T> void subscribeCommonReq(@NonNull Observable<Response<T>> observable, @NonNull Subscriber<T> subscriber, Action1<T> action1) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(action1)
                        .subscribe(subscriber)
        );
    }

    protected <T> void subscribeCommonReq(@NonNull Observable<Response<T>> observable, @NonNull ExObserver<T> observer) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(observer::onStart)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeCommonReq(@NonNull Observable<Response<T>> observable, Action1<T> action1) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(action1)
        );
    }

    protected <T> void subscribeReq(@NonNull Observable<Response<RespEntity<T>>> observable, ExObserver<T> observer) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(this::postError)
                        .doOnSubscribe(observer::onStart)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeReq(@NonNull Observable<Response<RespEntity<T>>> observable, ExObserver<T> observer, Action1<T> doOnNext) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(observer::onStart)
                        .doOnNext(doOnNext)
                        .subscribe(observer)
        );
    }

    protected <T> void subscribeReq(@NonNull Observable<Response<RespEntity<T>>> observable, @NonNull Subscriber<T> subscriber, Action1<T> doOnNext) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::respEntityFlatMap)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(doOnNext)
                        .subscribe(subscriber)
        );
    }

    protected <T extends RespEntity> void subscribeNoResp(@NonNull Observable<Response<T>> observable, @NonNull Subscriber<T> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::responseFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    protected <T extends RespEntity> void subscribeNoResp(@NonNull Observable<Response<T>> observable, @NonNull Subscriber<T> subscriber, Action1<T> doOnNext) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::aliveFilter)
                        .flatMap(this::responseFlatMap)
                        .flatMap(this::responseFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(doOnNext)
                        .doOnNext((t) -> Timber.i("doOnNext %s  %d", isAlive, Thread.currentThread().getId()))
                        .subscribe(subscriber)
        );
    }

//    private <T> Observable<T> doOn(T t) {
//        return Observable.create((Subscriber<? super T> subscriber) -> subscriber.onNext(t))
//                .doOnUnsubscribe(this::unSubscribe)
//                .doOnError(this::postError);
//    }

//    private <T> Observable<T> filterResp(Response<T> response) {
//        return Observable.create((Subscriber<? super Response<T>> subscriber) -> subscriber.onNext(response))
//                .filter(this::aliveFilter)
//                .flatMap(this::responseFlatMap);
//    }

    protected <T> void subscribeAsync(@NonNull Observable<T> observable, @NonNull Subscriber<T> subscriber) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::asyncRunFilter)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber)
        );
    }

    protected <T> void subscribeAsync(@NonNull Observable<T> observable, @NonNull ExObserver<T> observer) {
        subscription.add(
                observable
                        .subscribeOn(Schedulers.io())
                        .doOnUnsubscribe(this::unSubscribe)
                        .doOnError(this::postError)
                        .filter(this::asyncRunFilter)
                        .flatMap(this::dataFlatMap)
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(observer::onStart)
                        .subscribe(observer)
        );
    }

    private <Q> boolean aliveFilter(Response<Q> response) {
        return isAlive;
    }

    private <S> boolean asyncRunFilter(S s) {
        return isAlive;
    }

    protected boolean isAlive() {
        return isAlive;
    }

    public boolean isCancled() {
        return !isAlive;
    }

    private void unSubscribe() {
        Timber.i("unSubscribe %s  %d", isAlive, Thread.currentThread().getId());
    }

    private <M> Observable<M> responseFlatMap(Response<M> response) {
        return Observable.create((subscriber) -> {
                    if (response.isSuccess()) {
                        subscriber.onNext(response.body());
                    } else {
                        ErrorEvent errorEvent = new ErrorEvent(response.code(), response.message());
                        subscriber.onError(errorEvent);
                        postError(errorEvent);
                    }
                }
        );
    }

    private <Z> Observable<Z> respEntityFlatMap(RespEntity<Z> respEntity) {
        return Observable.create((subscriber) -> {
            if (respEntity == null) {
                subscriber.onError(new ErrorEvent(RespEntity.RESP_BODY_EMPTY, "Response entity is empty."));
            } else if (respEntity.getCode() == RespEntity.FAILURE) {
                subscriber.onError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
            } else if (respEntity.getCode() != RespEntity.SUCCESS) {
                postError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
                subscriber.onError(new Exception(respEntity.getMessage()));
//                subscriber.onNext(respEntity.getData());
            } else {
                subscriber.onNext(respEntity.getData());
            }
        });
    }

    private <Y extends RespEntity> Observable<Y> responseFlatMap(Y respEntity) {
        return Observable.create((subscriber) -> {
            if (respEntity == null) {
                subscriber.onError(new ErrorEvent(RespEntity.RESP_BODY_EMPTY, "Response entity is empty."));
            } else if (respEntity.getCode() == RespEntity.FAILURE || respEntity.getCode() != RespEntity.SUCCESS) {
                postError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
                subscriber.onError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
            }
//            else if () {
//                postError(new ErrorEvent(respEntity.getCode(), respEntity.getMessage()));
//                subscriber.onNext(respEntity);
//            }
            else {
                subscriber.onNext(respEntity);
            }
        });
    }

    private <N> Observable<N> dataFlatMap(N dataEntity) {
        return Observable.create((subscriber) -> {
                    if (dataEntity == null) {
                        subscriber.onError(new ErrorEvent(RespEntity.RESP_BODY_EMPTY, "Response Data is empty."));
                    } else {
                        subscriber.onNext(dataEntity);
                    }
                }
        );
    }

    private void postError(Throwable throwable) {
        ErrorEvent errorEvent;
        if (throwable instanceof ErrorEvent) {
            errorEvent = (ErrorEvent) throwable;
        } else if (throwable instanceof IOException) {
            errorEvent = new ErrorEvent(ErrorType.ERROR_NETWORK, "请求数据失败");
        } else {
            errorEvent = new ErrorEvent(ErrorType.ERROR_OTHER, throwable.getMessage());
        }
        RxBus.instance().post(errorEvent);
    }

    public static RequestBody convertToText(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

    protected Observable<Response<RespEntity>> mockRespObservable() {
        return Observable
                .create(new Observable.OnSubscribe<Response<RespEntity>>() {
                    @Override
                    public void call(Subscriber<? super Response<RespEntity>> subscriber) {
                        subscriber.onNext(Response.success(new RespEntity(RespEntity.SUCCESS)));
                    }
                })
                .delay(3, TimeUnit.SECONDS);
    }

    protected <T> Observable<Response<RespEntity<T>>> mockRespObservable(T t) {
        return Observable
                .create(new Observable.OnSubscribe<Response<RespEntity<T>>>() {
                    @Override
                    public void call(Subscriber<? super Response<RespEntity<T>>> subscriber) {
                        subscriber.onNext(Response.success(new RespEntity<>(RespEntity.SUCCESS, t)));
                    }
                })
                .delay(3, TimeUnit.SECONDS);
    }

}
