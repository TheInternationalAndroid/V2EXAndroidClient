package com.rayman.v2ex.ui.view.node;

import com.rayman.v2ex.model.http.service.NodeService;
import com.rayman.v2ex.model.http.service.TopicService;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/6/16
 * Time: 6:09 PM
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
public class NodeP extends BasePresenter implements NodeContract.Presenter {

    private final NodeService nodeService;
    private final TopicService topicService;

    @Inject
    public NodeP(RefWatcher refWatcher, NodeService nodeService, TopicService topicService) {
        super(refWatcher);
        this.nodeService = nodeService;
        this.topicService = topicService;
    }

    @Override
    public void requestNodeByName(String name, Subscriber<NodeEntity> subscriber) {
        subscribeHttpReq(nodeService.nodeByName(name), subscriber);
    }

    @Override
    public void requestTopicsByNodeId(long id, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicsByNodeId(id), subscriber);
    }

    @Override
    public void requestTopicsByNodeName(String name, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicsByNodeName(name), subscriber);
    }
}
