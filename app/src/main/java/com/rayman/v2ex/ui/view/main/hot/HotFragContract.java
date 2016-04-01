package com.rayman.v2ex.ui.view.main.hot;

import com.rayman.v2ex.di.scope.PerBindingFragment;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.ui.view.base.comp.FragmentComp;
import com.rayman.v2ex.ui.view.ITopicCellView;
import com.rayman.v2ex.viewmodel.main.HotFragVM;
import com.rayman.v2ex.viewmodel.main.HotFragVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 3:10 PM
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
public interface HotFragContract {

    @PerBindingFragment
    @Component(modules = {HotFragVMModule.class}, dependencies = FragmentComp.class)
    interface Comp extends FragmentComp {

        void inject(HotFragment hotFragment);

        HotFragVM viewModel();

        HotFragP presenter();
    }

    interface Presenter extends IPresenter {
        void requestHotList(Subscriber<List<TopicEntity>> subscriber);
    }

    interface View extends ITopicCellView {
    }

}
