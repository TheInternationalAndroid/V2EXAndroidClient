/*
 * Copyright (c) 2016 Lena.t.Yan
 * Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 * Created on 1/20/16 12:18 PM
 * ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 * Author: Lena; Last Modified: 1/20/16 12:18 PM.
 * This file is originally created by Lena.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.rayman.v2ex.vm.topic;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.rayman.v2ex.BR;
import com.rayman.v2ex.R;
import com.rayman.v2ex.anotations.ViewClick;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.main.ITopicCellView;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 12:18
 * \ ___________________
 * \| Happy New Year!  |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class TopicCellVM extends BaseObservable {

    private TopicEntity topic;
    private ITopicCellView onTopicCellClicked;

    public TopicCellVM(TopicEntity topic) {
        this.topic = topic;
    }

    public TopicCellVM(TopicEntity topic, ITopicCellView onTopicCellClicked) {
        this.topic = topic;
        this.onTopicCellClicked = onTopicCellClicked;
    }

    @Bindable
    public TopicEntity getTopic() {
        return topic;
    }

    @ViewClick(R.id.content)
    public void onContentClicked(View view) {
        if (onTopicCellClicked != null)
            onTopicCellClicked.onTopicCliced(topic);
    }

    @ViewClick(R.id.image)
    public void onAvatarClicked(View view) {
        if (onTopicCellClicked != null)
            onTopicCellClicked.onUserClicked(topic.getMember());
    }

    @ViewClick(R.id.node)
    public void onNodeClicked(View view) {
        if (onTopicCellClicked != null)
            onTopicCellClicked.onNodeClicked(topic.getNode());
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
        notifyPropertyChanged(BR.topic);
    }

}
