package com.rayman.v2ex.viewmodel.node;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.android.databinding.library.baseAdapters.BR;
import com.rayman.v2ex.model.http.LSubscriber;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.NodePageAdapter;
import com.rayman.v2ex.ui.view.node.NodeContract;
import com.rayman.v2ex.viewmodel.BaseVM;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/7/16
 * Time: 10:23 AM
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
public class NodeVM extends BaseVM<NodeContract.Presenter, NodeContract.View> {

    private final NodePageAdapter adapter;
    private final RecyclerView.LayoutManager layoutManager;

    private NodeEntity nodeEntity;

    public NodeVM(NodeContract.Presenter presenter, NodeContract.View view, RecyclerView.LayoutManager layoutManager) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new NodePageAdapter(view);
    }

    public void init(NodeEntity nodeEntity) {
        setNodeEntity(nodeEntity);
        requestNodeDetail();
        requestTopicList();
    }

    public void setNodeEntity(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
        adapter.setNodeEntity(nodeEntity);
        notifyPropertyChanged(BR.nodeEntity);
    }

    @Bindable
    public NodeEntity getNodeEntity() {
        return nodeEntity;
    }

    public NodePageAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    private void requestNodeDetail() {
        presenter.requestNodeByName(nodeEntity.getName(), new LSubscriber<NodeEntity>() {

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(NodeEntity nodeEntity) {
                if (nodeEntity != null)
                    setNodeEntity(nodeEntity);
            }
        });
    }

    private void requestTopicList() {
        presenter.requestTopicsByNodeId(nodeEntity.getId(), new LSubscriber<List<TopicEntity>>() {
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(List<TopicEntity> topicEntities) {
                if (topicEntities != null && topicEntities.size() > 0) {
                    adapter.setList(topicEntities);
                }
            }
        });
    }

}
