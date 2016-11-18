/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Fri, 11 Nov 2016 23:40:53 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Fri, 11 Nov 2016 23:40:53 +0800.
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

package com.ray.sample.v2ex.view.v2ex.presenter;

import com.ray.mvvm.lib.db.ITopicDBManager;
import com.ray.mvvm.lib.model.http.ExObserver;
import com.ray.mvvm.lib.model.http.ExSubscriber;
import com.ray.mvvm.lib.model.model.member.MemberEntity;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.model.service.TopicService;
import com.ray.mvvm.lib.presenter.BasePresenter;
import com.ray.sample.v2ex.view.v2ex.contract.TopicListContract;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class TopicListP extends BasePresenter implements TopicListContract.Presenter {

    private final TopicService topicService;
    private final ITopicDBManager topicDBManager;

    @Inject
    TopicListP(RefWatcher refWatcher, TopicService topicService, ITopicDBManager topicDBManager) {
        super(refWatcher);
        this.topicService = topicService;
        this.topicDBManager = topicDBManager;
    }

    @Override
    public void requestTopicList(ExObserver<List<TopicEntity>> observer) {
        subscribeCommonReqConcat(
                topicService.hot(),
                (topicEntities) ->
                        Observable.create(subscriber -> {
                            for (TopicEntity topicEntity : topicEntities) {
                                MemberEntity memberEntity = topicEntity.getMember();
                                memberEntity.setAvatarNormal("http:" + memberEntity.getAvatarNormal());
                                memberEntity.setAvatarMini("http:" + memberEntity.getAvatarMini());
                                memberEntity.setAvatarLarge("http:" + memberEntity.getAvatarLarge());
                            }
                            subscriber.onNext(topicEntities);
                            subscriber.onCompleted();
                        }),
                topicDBManager::insertListObs,
                observer
        );
    }

    @Override
    public void findTopicList(ExSubscriber<List<TopicEntity>> subscriber) {
        subscribe(topicDBManager.findAllObs().subscribe(subscriber));
    }

}
