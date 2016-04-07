package com.rayman.v2ex.viewmodel.node;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.di.modules.LayoutManagerModule;
import com.rayman.v2ex.di.scope.PerActivity;
import com.rayman.v2ex.ui.view.node.NodeContract;
import com.rayman.v2ex.ui.view.node.NodeP;
import com.rayman.v2ex.widget.anotations.ListType;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/7/16
 * Time: 10:22 AM
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
@Module(includes = {LayoutManagerModule.class})
public class NodeVMModule {

    private NodeContract.View view;

    public NodeVMModule(NodeContract.View view) {
        this.view = view;
    }

    @Provides
    @PerActivity
    NodeVM provideNodeVM(NodeP presenter, @Named(ListType.VERTICAL) RecyclerView.LayoutManager layoutManager) {
        return new NodeVM(presenter, view, layoutManager);
    }

}
