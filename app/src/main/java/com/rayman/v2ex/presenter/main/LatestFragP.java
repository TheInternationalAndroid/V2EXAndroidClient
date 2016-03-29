/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 4:36 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: LatestFragP.
 * Author: Lena; Last Modified: 1/19/16 4:36 PM.
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

package com.rayman.v2ex.presenter.main;

import com.rayman.v2ex.model.http.service.TopicService;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 16:36
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
public class LatestFragP extends BasePresenter implements ILatestFragP {

    private TopicService topicService;

    @Inject
    public LatestFragP(TopicService topicService, RefWatcher refWatcher) {
        super(refWatcher);
        this.topicService = topicService;
    }

    @Override
    public void requestLatestList(Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.latest(), subscriber);
    }
}
