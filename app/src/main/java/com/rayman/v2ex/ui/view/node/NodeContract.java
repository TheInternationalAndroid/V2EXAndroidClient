package com.rayman.v2ex.ui.view.node;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.ui.view.base.view.IView;
import com.rayman.v2ex.ui.view.common.ITopicCellView;
import com.rayman.v2ex.viewmodel.node.NodeVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/6/16
 * Time: 6:07 PM
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
public interface NodeContract {

    @PerActivity
    @Component(modules = {NodeVMModule.class}, dependencies = ActivityComp.class)
    interface NodeComp {
        void inject(NodeActivity nodeActivity);
    }

    interface View extends IView, ITopicCellView {
    }

    interface Presenter extends IPresenter {

        void requestNodeByName(String name, Subscriber<NodeEntity> subscriber);

        void requestTopicsByNodeId(long id, Subscriber<List<TopicEntity>> subscriber);

        void requestTopicsByNodeName(String name, Subscriber<List<TopicEntity>> subscriber);

    }

}
