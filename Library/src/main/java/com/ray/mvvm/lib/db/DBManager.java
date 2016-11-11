/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 23:29:20 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 23:29:20 +0800.
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

package com.ray.mvvm.lib.db;

import com.ray.mvvm.lib.widget.utils.StringUtil;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import timber.log.Timber;

public abstract class DBManager<T extends RealmModel> implements IDBManager<T> {

    static final long DATA_CACHE_TIME_LIMIT_DEFAULT = 7 * 24 * 60 * 60;
    static final int DATA_CACHE_COUNT_LIMIT_DEFAULT = 300;

    protected final Realm realm;
    private final Class<T> clazz;

    protected DBManager(Realm realm, Class<T> clazz) {
        this.realm = realm;
        this.clazz = clazz;
    }

    protected abstract String getPrimaryKey();

    @Override
    public Observable<T> findItemByPoisitionObs(int position) {
        return Observable.create((subscribe) -> {
                    RealmResults<T> results = realm.where(clazz).findAll();
                    if (results.size() == 0) {
                        subscribe.onNext(null);
                    } else {
                        subscribe.onNext(results.get(position));
                    }
                    subscribe.onCompleted();
                }
        );
    }

    @Override
    public T findItemById(long id) {
        return realm.where(clazz).equalTo(getPrimaryKey(), id).findFirst();
    }

    @Override
    public Observable<T> findFirstObs() {
        return Observable.create((onSubscribe) -> {
                    T t = realm.where(clazz).findFirst();
                    RealmResults<T> results = realm.where(clazz).findAll();
                    Timber.i("Count + " + results.size());
                    onSubscribe.onNext(t);
                    onSubscribe.onCompleted();
                }
        );
    }

    @Override
    public T findFirst() {
        RealmResults<T> results = realm.where(clazz).findAll();
        if (results.size() == 0) {
            return null;
        } else {
            return results.first();
        }
    }

    @Override
    public T findLast() {
        RealmResults<T> results = realm.where(clazz).findAll();
        if (results.size() == 0) {
            return null;
        } else {
            return results.last();
        }
    }

    @Override
    public Observable<T> findLastObs() {
        return Observable.create((onSubscribe) -> {
                    RealmResults<T> results = realm.where(clazz).findAll();
                    if (results.size() == 0) {
                        onSubscribe.onNext(null);
                    } else {
                        onSubscribe.onNext(results.last());
                    }
                    onSubscribe.onCompleted();
                }
        );
    }

    @Override
    public Observable<List<T>> findAllObs() {
        return Observable.create(subscriber -> {
                    RealmResults<T> realmResults = realm.where(clazz).findAll();
                    if (realmResults.size() == 0) {
                        subscriber.onNext(null);
                    } else {
                        subscriber.onNext(realmResults);
                    }
                    subscriber.onCompleted();
                }
        );
    }

    @Override
    public Observable<T> insertItemObs(T t) {
        return Observable
                .create(subscriber ->
                        realm.executeTransaction(realm -> {
                                    subscriber.onNext(realm.copyToRealmOrUpdate(t));
                                    subscriber.onCompleted();
                                }
                        ));
    }

    @Override
    public Observable<T> updateItemObs(T t) {
        return Observable
                .create(subscriber ->
                        realm.executeTransaction(realm -> {
                                    subscriber.onNext(realm.copyToRealmOrUpdate(t));
                                    subscriber.onCompleted();
                                }
                        ));
    }

    @Override
    public Observable<Boolean> updateItemAsync(T t) {
        return Observable
                .create(subscriber ->
                        realm.executeTransactionAsync(realm ->
                                        realm.copyToRealmOrUpdate(t)
                                , () -> {
                                    subscriber.onNext(true);
                                    subscriber.onCompleted();
                                },
                                subscriber::onError
                        ));
    }

    @Override
    public Observable<List<T>> insertListObs(List<T> list) {
        return Observable
                .create(subscriber -> {
                    subscriber.onNext(realm.copyToRealmOrUpdate(list));
                    subscriber.onCompleted();
                });
    }

    @Override
    public Observable<Boolean> removeItemAsync(long id) {
        return Observable
                .create(subscriber ->
                        realm.executeTransactionAsync(realm ->
                                        Observable
                                                .create((Subscriber<? super Boolean> subscriber1) -> {
                                                    try {
                                                        RealmObject.deleteFromRealm(realm.where(clazz).equalTo(getPrimaryKey(), id).findFirst());
                                                        subscriber1.onNext(true);
                                                        subscriber1.onCompleted();
                                                    } catch (Exception e) {
                                                        subscriber1.onError(e);
                                                    }
                                                })
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .doOnNext(subscriber::onNext)
                                                .subscribe(),
                                subscriber::onCompleted,
                                subscriber::onError)
                );
    }

    @Override
    public Observable<Boolean> removeAllAsync() {
        return Observable
                .create((subscriber) ->
                        realm.executeTransactionAsync(realm ->
                                        Observable
                                                .create((Subscriber<? super Boolean> subscriber1) -> {
                                                    realm.where(clazz).findAll().deleteAllFromRealm();
                                                    subscriber1.onNext(true);
                                                    subscriber1.onCompleted();
                                                })
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .doOnNext(subscriber::onNext)
                                                .subscribe(),
                                subscriber::onCompleted,
                                subscriber::onError)
                );
    }

    @Override
    public Observable<Boolean> volumeCheckAsync() {
        return Observable
                .create((subscriber) ->
                        realm.executeTransactionAsync(realm ->
                                        Observable
                                                .create((Subscriber<? super Boolean> subscriber1) -> {
                                                    long current = System.currentTimeMillis() / 1000;
                                                    String updateTimeField = getUpdateTimeField();
                                                    if (!StringUtil.isEmpty(updateTimeField))
                                                        realm.where(clazz).lessThan(updateTimeField, current - DATA_CACHE_TIME_LIMIT_DEFAULT).findAll().deleteAllFromRealm();
                                                    RealmResults<T> realmResults = realm.where(clazz).findAll();
                                                    final int totalCount = realmResults.size();
                                                    if (totalCount > DATA_CACHE_COUNT_LIMIT_DEFAULT) {
                                                        final int start = DATA_CACHE_COUNT_LIMIT_DEFAULT;
                                                        final int end = totalCount - 1;
                                                        for (int i = start; i < end; i++) {
                                                            realmResults.deleteLastFromRealm();
                                                        }
                                                    }
                                                    Timber.i("Result" + realmResults.size());
                                                    subscriber1.onNext(true);
                                                    subscriber1.onCompleted();
                                                })
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .doOnNext(subscriber::onNext)
                                                .subscribe(),
                                subscriber::onCompleted,
                                subscriber::onError)
                );
    }

    public String getUpdateTimeField() {
        return null;
    }

    @Override
    public Observable<Boolean> runActionAsync(Action0 action0) {
        return Observable
                .create((subscriber) ->
                        realm.executeTransactionAsync(
                                realm ->
                                        action0.call(),
                                () -> {
                                    subscriber.onNext(true);
                                    subscriber.onCompleted();
                                },
                                subscriber::onError)
                );
    }

    @Override
    public boolean runAction(Action0 action0) {
        try {
            realm.executeTransaction(realm ->
                    action0.call());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
