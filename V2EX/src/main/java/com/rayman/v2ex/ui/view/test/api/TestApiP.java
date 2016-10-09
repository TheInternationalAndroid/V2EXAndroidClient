/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 22:22:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 22:22:35 +0800.
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

package com.rayman.v2ex.ui.view.test.api;

import com.ray.mvvm.lib.model.http.service.MemberService;
import com.ray.mvvm.lib.model.http.service.NodeService;
import com.ray.mvvm.lib.model.http.service.ReplyService;
import com.ray.mvvm.lib.model.http.service.TopicService;
import com.ray.mvvm.lib.model.model.member.MemberEntity;
import com.ray.mvvm.lib.model.model.node.NodeEntity;
import com.ray.mvvm.lib.model.model.reply.ReplyEntity;
import com.ray.mvvm.lib.model.model.topic.TopicEntity;
import com.ray.mvvm.lib.view.base.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 2:40 PM
 * \ ----------------------------------------
 * \| A small leak will sink a great ship.!  |
 * \ ----------------------------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class TestApiP extends BasePresenter implements TestApiContract.Presenter {

    private final TopicService topicService;
    private final NodeService nodeService;
    private final MemberService memberService;
    private final ReplyService replyService;

    @Inject
    public TestApiP(RefWatcher refWatcher, TopicService topicService, NodeService nodeService, MemberService memberService, ReplyService replyService) {
        super(refWatcher);
        this.topicService = topicService;
        this.nodeService = nodeService;
        this.memberService = memberService;
        this.replyService = replyService;
    }

    @Override
    public void requestHotList(Subscriber<List<TopicEntity>> subscriber) {
        subscribeCommonReq(topicService.hot(), subscriber);
    }

    @Override
    public void requestLastestList(Subscriber<List<TopicEntity>> subscriber) {
        subscribeCommonReq(topicService.latest(), subscriber);
    }

    @Override
    public void requestTopicListByName(String userName, Subscriber<List<TopicEntity>> subscriber) {
        subscribeCommonReq(topicService.topicsByUserName(userName), subscriber);
    }

    @Override
    public void requestNode(String nodeName, Subscriber<NodeEntity> subscriber) {
        subscribeCommonReq(nodeService.nodeByName(nodeName), subscriber);
    }

    @Override
    public void requestTopicListByNodeId(long nodeId, Subscriber<List<TopicEntity>> subscriber) {
        subscribeCommonReq(topicService.topicsByNodeId(nodeId), subscriber);
    }

    @Override
    public void requestTopicListByNodeName(String nodeName, Subscriber<List<TopicEntity>> subscriber) {
        subscribeCommonReq(topicService.topicsByNodeName(nodeName), subscriber);
    }

    @Override
    public void requestTopicById(long topicId, Subscriber<List<TopicEntity>> subscriber) {
        subscribeCommonReq(topicService.topicById(topicId), subscriber);
    }

    @Override
    public void requestNodelist(Subscriber<List<NodeEntity>> subscriber) {
        subscribeCommonReq(nodeService.nodes(), subscriber);
    }

    @Override
    public void requestReplies(long topicId, Subscriber<List<ReplyEntity>> subscriber) {
        subscribeCommonReq(replyService.replies(topicId), subscriber);
    }

    @Override
    public void requestMember(String userName, Subscriber<MemberEntity> subscriber) {
        subscribeCommonReq(memberService.member(userName), subscriber);
    }
}
