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

import java.util.List;

import io.realm.RealmModel;
import rx.Observable;
import rx.functions.Action0;

public interface IDBManager<T extends RealmModel> {

    T findItemById(long id);

    Observable<T> findItemByPoisitionObs(int position);

    Observable<T> findFirstObs();

    Observable<T> findLastObs();

    T findFirst();

    T findLast();

    Observable<List<T>> findAllObs();

    Observable<List<T>> insertListObs(List<T> list);

    Observable<T> insertItemObs(T t);

    Observable<T> updateItemObs(T t);

    Observable<Boolean> updateItemAsync(T t);

    Observable<Boolean> removeItemAsync(long id);

    Observable<Boolean> removeAllAsync();

    Observable<Boolean> runActionAsync(Action0 action0);

    Observable<Boolean> volumeCheckAsync();

    boolean runAction(Action0 action0);

}
