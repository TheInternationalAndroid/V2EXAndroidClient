package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.model.http.callback.LSubscriber;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.model.worker.MemberWorker;
import com.rayman.v2ex.model.worker.NodeWorker;
import com.rayman.v2ex.model.worker.ReplyWorker;
import com.rayman.v2ex.model.worker.TopicWorker;
import com.rayman.v2ex.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

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

    private final TopicWorker topicWorker;
    private final NodeWorker nodeWorker;
    private final MemberWorker memberWorker;
    private final ReplyWorker replyWorker;

    @Inject
    public TestApiP(RefWatcher refWatcher, TopicWorker topicWorker, NodeWorker nodeWorker, MemberWorker memberWorker, ReplyWorker replyWorker) {
        super(refWatcher, topicWorker, nodeWorker, memberWorker, replyWorker);
        this.topicWorker = topicWorker;
        this.nodeWorker = nodeWorker;
        this.memberWorker = memberWorker;
        this.replyWorker = replyWorker;
    }

    @Override
    public void requestHotList(LSubscriber<List<TopicEntity>> subscriber) {
        topicWorker.hot(subscriber);
    }

    @Override
    public void requestLastestList(LSubscriber<List<TopicEntity>> subscriber) {
        topicWorker.latest(subscriber);
    }

    @Override
    public void requestTopicListByName(String userName, LSubscriber<List<TopicEntity>> subscriber) {
        topicWorker.topics(userName, subscriber);
    }

    @Override
    public void requestNode(String nodeName, LSubscriber<NodeEntity> subscriber) {
        nodeWorker.node(nodeName, subscriber);
    }

    @Override
    public void requestTopicListByNodeId(long nodeId, LSubscriber<List<TopicEntity>> subscriber) {
        topicWorker.topicsByNodeId(nodeId, subscriber);
    }

    @Override
    public void requestTopicListByNodeName(String nodeName, LSubscriber<List<TopicEntity>> subscriber) {
        topicWorker.topicsByNodeName(nodeName, subscriber);
    }

    @Override
    public void requestTopicById(long topicId, LSubscriber<List<TopicEntity>> subscriber) {
        topicWorker.topicById(topicId, subscriber);
    }

    @Override
    public void requestNodelist(LSubscriber<List<NodeEntity>> subscriber) {
        nodeWorker.nodeList(subscriber);
    }

    @Override
    public void requestReplies(long topicId, LSubscriber<List<ReplyEntity>> subscriber) {
        replyWorker.replies(topicId, subscriber);
    }

    @Override
    public void requestMember(String userName, LSubscriber<MemberEntity> subscriber) {
        memberWorker.member(userName, subscriber);
    }
}
