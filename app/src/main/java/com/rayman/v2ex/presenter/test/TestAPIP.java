package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.model.http.callback.ReqCallback;
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
    public void requestHotList(ReqCallback<List<TopicEntity>> callback) {
        topicWorker.hot(callback);
    }

    @Override
    public void requestLastestList(ReqCallback<List<TopicEntity>> callback) {
        topicWorker.latest(callback);
    }

    @Override
    public void requestTopicListByName(String userName, ReqCallback<List<TopicEntity>> callback) {
        topicWorker.topics(userName, callback);
    }

    @Override
    public void requestNode(String nodeName, ReqCallback<NodeEntity> callback) {
        nodeWorker.node(nodeName, callback);
    }

    @Override
    public void requestTopicListByNodeId(long nodeId, ReqCallback<List<TopicEntity>> callback) {
        topicWorker.topicsByNodeId(nodeId, callback);
    }

    @Override
    public void requestTopicListByNodeName(String nodeName, ReqCallback<List<TopicEntity>> callback) {
        topicWorker.topicsByNodeName(nodeName, callback);
    }

    @Override
    public void requestTopicById(long topicId, ReqCallback<List<TopicEntity>> callback) {
        topicWorker.topicById(topicId, callback);
    }

    @Override
    public void requestNodelist(ReqCallback<List<NodeEntity>> callback) {
        nodeWorker.nodeList(callback);
    }

    @Override
    public void requestReplies(long topicId, ReqCallback<List<ReplyEntity>> callback) {
        replyWorker.replies(topicId, callback);
    }

    @Override
    public void requestMember(String userName, ReqCallback<MemberEntity> callback) {
        memberWorker.member(userName, callback);
    }
}
