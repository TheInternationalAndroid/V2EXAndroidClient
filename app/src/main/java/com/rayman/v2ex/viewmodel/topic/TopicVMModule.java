package com.rayman.v2ex.viewmodel.topic;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.topic.TopicContract;
import com.rayman.v2ex.ui.view.topic.TopicP;
import com.rayman.v2ex.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 3:30 PM
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
@Module
public class TopicVMModule {

    TopicContract.View view;

    public TopicVMModule(TopicContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    TopicVM provideTopicVM(TopicP topicP, @Named(ListType.VERTICAL) RecyclerView.LayoutManager layoutManager) {
        return new TopicVM(topicP, view, layoutManager);
    }

}
