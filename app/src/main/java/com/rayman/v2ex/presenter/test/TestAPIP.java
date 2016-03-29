package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.model.http.service.MemberService;
import com.rayman.v2ex.model.http.service.NodeService;
import com.rayman.v2ex.model.http.service.ReplyService;
import com.rayman.v2ex.model.http.service.TopicService;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.BasePresenter;
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
public class TestApiP extends BasePresenter implements ITestApiP {

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
        subscribeHttpReq(topicService.hot(), subscriber);
    }

    @Override
    public void requestLastestList(Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.latest(), subscriber);
    }

    @Override
    public void requestTopicListByName(String userName, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicsByUserName(userName), subscriber);
    }

    @Override
    public void requestNode(String nodeName, Subscriber<NodeEntity> subscriber) {
        subscribeHttpReq(nodeService.nodeByName(nodeName), subscriber);
    }

    @Override
    public void requestTopicListByNodeId(long nodeId, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicsByNodeId(nodeId), subscriber);
    }

    @Override
    public void requestTopicListByNodeName(String nodeName, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicsByNodeName(nodeName), subscriber);
    }

    @Override
    public void requestTopicById(long topicId, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicById(topicId), subscriber);
    }

    @Override
    public void requestNodelist(Subscriber<List<NodeEntity>> subscriber) {
        subscribeHttpReq(nodeService.nodes(), subscriber);
    }

    @Override
    public void requestReplies(long topicId, Subscriber<List<ReplyEntity>> subscriber) {
        subscribeHttpReq(replyService.replies(topicId), subscriber);
    }

    @Override
    public void requestMember(String userName, Subscriber<MemberEntity> subscriber) {
        subscribeHttpReq(memberService.member(userName), subscriber);
    }
}
