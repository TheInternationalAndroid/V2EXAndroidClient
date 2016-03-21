package com.rayman.v2ex.presenter.test;

import com.rayman.v2ex.model.http.callback.ReqCallback;
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

    void requestLastestList(ReqCallback<List<TopicEntity>> callback);

    void requestHotList(ReqCallback<List<TopicEntity>> callback);

    void requestTopicListByName(String name, ReqCallback<List<TopicEntity>> callback);

    void requestTopicListByNodeId(long nodeId, ReqCallback<List<TopicEntity>> callback);

    void requestTopicListByNodeName(String nodeName, ReqCallback<List<TopicEntity>> callback);

    void requestTopicById(long topicId, ReqCallback<List<TopicEntity>> callback);

    void requestNode(String name, ReqCallback<NodeEntity> callback);

    void requestNodelist(ReqCallback<List<NodeEntity>> callback);

    void requestReplies(long topicId, ReqCallback<List<ReplyEntity>> callback);

    void requestMember(String userName, ReqCallback<MemberEntity> callback);
}
