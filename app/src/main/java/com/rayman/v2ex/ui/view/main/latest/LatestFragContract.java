package com.rayman.v2ex.ui.view.main.latest;

import com.rayman.v2ex.di.scope.PerBindingFragment;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.common.IMemberCellView;
import com.rayman.v2ex.ui.view.common.ITopicCellView;
import com.rayman.v2ex.ui.view.base.comp.FragmentComp;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.viewmodel.main.LatestFragVM;
import com.rayman.v2ex.viewmodel.main.LatestFragVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 3:19 PM
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
public interface LatestFragContract {

    @PerBindingFragment
    @Component(modules = {LatestFragVMModule.class}, dependencies = FragmentComp.class)
    interface Comp extends FragmentComp {

        void inject(LatestFragment latestFragment);

        LatestFragVM viewModel();

        LatestFragP presenter();
    }

    interface Presenter extends IPresenter {
        void requestLatestList(Subscriber<List<TopicEntity>> subscriber);
    }

    interface View extends ITopicCellView, IMemberCellView {
    }

}
