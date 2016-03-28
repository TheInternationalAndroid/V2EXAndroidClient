package com.rayman.v2ex.model.worker;

import com.rayman.v2ex.model.http.callback.LSubscriber;
import com.rayman.v2ex.model.http.service.NodeService;
import com.rayman.v2ex.model.model.node.NodeEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 3:53 PM
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
public class NodeWorker extends BaseWorker {

    private NodeService nodeService;

    @Inject
    public NodeWorker(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public void node(String name, LSubscriber<NodeEntity> subscriber) {
        defaultCall(nodeService.nodeByName(name), subscriber);
    }

    public void nodeList(LSubscriber<List<NodeEntity>> subscriber) {
        defaultCall(nodeService.nodes(), subscriber);
    }

}
