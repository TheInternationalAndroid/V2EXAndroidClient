package com.rayman.v2ex.viewmodel.topic;

import android.databinding.Bindable;
import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.BR;
import com.rayman.v2ex.model.http.LSubscriber;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.TopicPageAdapter;
import com.rayman.v2ex.ui.view.topic.TopicContract;
import com.rayman.v2ex.viewmodel.BaseVM;

import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 3:29 PM
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
public class TopicVM extends BaseVM<TopicContract.Preenter, TopicContract.View> {

    private TopicEntity entity;
    private final RecyclerView.LayoutManager layoutManager;
    private final TopicPageAdapter adapter;

    public TopicVM(TopicContract.Preenter presenter, TopicContract.View view, RecyclerView.LayoutManager layoutManager) {
        super(presenter, view);
        this.layoutManager = layoutManager;
        adapter = new TopicPageAdapter();
    }

    public void init(TopicEntity entity) {
        setEntity(entity);
        requestTopicDetail();
        requestReplies();
    }

    @Bindable
    public TopicEntity getEntity() {
        return entity;
    }

    public void setEntity(TopicEntity entity) {
        this.entity = entity;
        adapter.setTopicEntity(entity);
        notifyPropertyChanged(BR.entity);
    }

    private void requestTopicDetail() {
        presenter.requestDetail(entity.getId(), new LSubscriber<List<TopicEntity>>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<TopicEntity> topicEntities) {
                if (topicEntities != null && topicEntities.size() > 0) {
                    setEntity(topicEntities.get(0));
                }
            }
        });
    }

    private void requestReplies() {
        presenter.requestReplies(entity.getId(), new LSubscriber<List<ReplyEntity>>() {
            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ReplyEntity> replyEntities) {
            }
        });
    }

    public TopicPageAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }
}
