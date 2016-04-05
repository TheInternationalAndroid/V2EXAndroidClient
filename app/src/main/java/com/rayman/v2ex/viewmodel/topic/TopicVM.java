package com.rayman.v2ex.viewmodel.topic;

import com.rayman.v2ex.ui.view.topic.TopicContract;
import com.rayman.v2ex.viewmodel.BaseVM;

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

    public TopicVM(TopicContract.Preenter presenter, TopicContract.View view) {
        super(presenter, view);
    }
}
