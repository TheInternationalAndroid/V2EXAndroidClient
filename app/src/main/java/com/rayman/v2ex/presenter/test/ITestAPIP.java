package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.IPresenter;

import java.util.List;

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
public interface ITestApiP extends IPresenter {

    void requestLastestList(Subscriber<List<TopicEntity>> callback);

    void requestHotList(Subscriber<List<TopicEntity>> callback);

    void requestTopicListByName(String name, Subscriber<List<TopicEntity>> callback);

    void requestTopicListByNodeId(long nodeId, Subscriber<List<TopicEntity>> callback);

    void requestTopicListByNodeName(String nodeName, Subscriber<List<TopicEntity>> callback);

    void requestTopicById(long topicId, Subscriber<List<TopicEntity>> callback);

    void requestNode(String name, Subscriber<NodeEntity> callback);

    void requestNodelist(Subscriber<List<NodeEntity>> callback);

    void requestReplies(long topicId, Subscriber<List<ReplyEntity>> callback);

    void requestMember(String userName, Subscriber<MemberEntity> callback);
}
