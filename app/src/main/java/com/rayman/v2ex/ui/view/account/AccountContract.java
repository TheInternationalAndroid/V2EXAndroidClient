package com.rayman.v2ex.ui.view.account;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.presenter.IPresenter;
import com.rayman.v2ex.ui.view.base.comp.ActivityComp;
import com.rayman.v2ex.ui.view.base.view.IBaseView;
import com.rayman.v2ex.viewmodel.account.AccountVM;
import com.rayman.v2ex.viewmodel.account.AccountVMModule;

import java.util.List;

import dagger.Component;
import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/1/16
 * Time: 1:38 PM
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
public interface AccountContract {

    interface View extends IBaseView {
    }

    interface Presneter extends IPresenter {

        void requestTopicList(String userName, Subscriber<List<TopicEntity>> callback);

        void requestMemberDetail(String userName, Subscriber<MemberEntity> callback);

    }

    @PerActivity
    @Component(modules = AccountVMModule.class, dependencies = ActivityComp.class)
    interface Comp extends ActivityComp {

        void inject(AccountActivity accountActivity);

        AccountVM viewModel();

    }
}
