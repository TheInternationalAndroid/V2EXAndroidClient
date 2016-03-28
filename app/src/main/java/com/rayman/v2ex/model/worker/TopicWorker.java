/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/19/16 3:30 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicWorker.
 * Author: Lena; Last Modified: 1/19/16 3:30 PM.
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

import com.rayman.v2ex.model.http.callback.LSubscriber;
import com.rayman.v2ex.model.http.service.TopicService;
import com.rayman.v2ex.model.model.topic.TopicEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/19/16
 * Time: 15:30
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
public class TopicWorker extends BaseWorker {

    private TopicService topicService;

    @Inject
    public TopicWorker(TopicService topicService) {
        this.topicService = topicService;
    }

    public void hot(LSubscriber<List<TopicEntity>> subscriber) {
        defaultCall(topicService.hot(), subscriber);
    }

    public void latest(LSubscriber<List<TopicEntity>> subscriber) {
        defaultCall(topicService.latest(), subscriber);
    }

    public void topics(String userName, LSubscriber<List<TopicEntity>> subscriber) {
        defaultCall(topicService.topicsByUserName(userName), subscriber);
    }

    public void topicsByNodeId(long nodeId, LSubscriber<List<TopicEntity>> subscriber) {
        defaultCall(topicService.topicsByNodeId(nodeId), subscriber);
    }

    public void topicsByNodeName(String nodeName, LSubscriber<List<TopicEntity>> subscriber) {
        defaultCall(topicService.topicsByNodeName(nodeName), subscriber);
    }

    public void topicById(long nodeId, LSubscriber<List<TopicEntity>> subscriber) {
        defaultCall(topicService.topicById(nodeId), subscriber);
    }

}
