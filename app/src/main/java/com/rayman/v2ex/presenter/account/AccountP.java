/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 4:38 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: AccountP.
 * Author: Lena; Last Modified: 1/20/16 4:38 PM.
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

package com.rayman.v2ex.presenter.account;

import com.rayman.v2ex.http.callback.ReqCallback;
import com.rayman.v2ex.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.BasePresenter;
import com.rayman.v2ex.worker.TopicWorker;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 16:38
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
public class AccountP extends BasePresenter implements IAccountP {

    private TopicWorker topicWorker;

    @Inject public AccountP(TopicWorker topicWorker) {
        this.topicWorker = topicWorker;
        bindLifecycleCallback(topicWorker);
    }

    @Override public void topics(String userName, ReqCallback<List<TopicEntity>> callback) {
        topicWorker.topics(userName, callback);
    }

}