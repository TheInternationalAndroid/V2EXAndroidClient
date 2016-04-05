package com.rayman.v2ex.ui.adapter.list;

import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rayman.v2ex.databinding.ListCellReplyBinding;
import com.rayman.v2ex.databinding.ListHeaderTopicBinding;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.adapter.list.base.BaseHeaderAdapter;
import com.rayman.v2ex.ui.adapter.list.base.CellVM;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 5:34 PM
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
public class TopicPageAdapter extends BaseHeaderAdapter<TopicEntity> {

    private static final int VIEW_HEADER = 0;
    private static final int VIEW_CELL = 1;
    private static final int HEADER_COUNT = 1;
    private TopicEntity topicEntity;

    @Override
    protected int getHeaderCount() {
        return HEADER_COUNT;
    }

    @Override
    protected ViewDataBinding createBinding(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_HEADER:
                return ListHeaderTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            default:
            case VIEW_CELL:
                return ListCellReplyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_CELL;
        }
    }

    @Override
    protected Object createViewModel(int position) {
        switch (getItemViewType(position)) {
            case VIEW_HEADER:
                return new CellVM<>(topicEntity);
            default:
            case VIEW_CELL:
                return new CellVM<>(getItem(position));
        }
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
        notifyItemChanged(0);
    }
}
