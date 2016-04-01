/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 4:27 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: HotFragPresenter.
 * Author: Lena; Last Modified: 1/19/16 4:27 PM.
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

package com.rayman.v2ex.ui.view.main.hot;

import com.rayman.v2ex.model.http.service.TopicService;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 16:27
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
public class HotFragP extends BasePresenter implements HotFragContract.Presenter {

    private TopicService topicService;

    @Inject
    public HotFragP(TopicService topicService, RefWatcher refWatcher) {
        super(refWatcher);
        this.topicService = topicService;
    }

    @Override
    public void requestHotList(Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.hot(), subscriber);
    }
}
