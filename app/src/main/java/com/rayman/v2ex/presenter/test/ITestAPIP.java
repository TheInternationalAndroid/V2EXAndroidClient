package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.model.http.callback.LSubscriber;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.IPresenter;

import java.util.List;

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
public interface ITestApiP extends IPresenter {

    void requestLastestList(LSubscriber<List<TopicEntity>> callback);

    void requestHotList(LSubscriber<List<TopicEntity>> callback);

    void requestTopicListByName(String name, LSubscriber<List<TopicEntity>> callback);

    void requestTopicListByNodeId(long nodeId, LSubscriber<List<TopicEntity>> callback);

    void requestTopicListByNodeName(String nodeName, LSubscriber<List<TopicEntity>> callback);

    void requestTopicById(long topicId, LSubscriber<List<TopicEntity>> callback);

    void requestNode(String name, LSubscriber<NodeEntity> callback);

    void requestNodelist(LSubscriber<List<NodeEntity>> callback);

    void requestReplies(long topicId, LSubscriber<List<ReplyEntity>> callback);

    void requestMember(String userName, LSubscriber<MemberEntity> callback);
}
