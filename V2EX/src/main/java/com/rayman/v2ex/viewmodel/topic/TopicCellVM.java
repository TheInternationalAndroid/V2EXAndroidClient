/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sat, 8 Oct 2016 22:22:35 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sat, 8 Oct 2016 22:22:35 +0800.
 *  This file is originally created by Lena.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.rayman.v2ex.viewmodel.topic;

import android.view.View;

import com.ray.mvvm.lib.view.adapter.list.base.CellVM;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.common.ITopicCellView;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena.t.Yan
 * Date: 1/20/16
 * Time: 12:18
 * \ ___________________
 * \| Happy New Year!   |
 * \ -------------------
 * \  \
 * \   \   \_\_    _/_/
 * \    \      \__/
 * \           (oo)\_______
 * \           (__)\       )\/\
 * \               ||----w |
 * \               ||     ||
 */
public class TopicCellVM extends CellVM<TopicEntity> {

    private ITopicCellView onTopicCellClicked;

    public TopicCellVM(TopicEntity topic, ITopicCellView onTopicCellClicked) {
        super(topic);
        this.onTopicCellClicked = onTopicCellClicked;
    }

    public void onContentClicked(View view) {
        if (onTopicCellClicked != null)
            onTopicCellClicked.onTopicCliced(entity);
    }

    public void onAvatarClicked(View view) {
        if (onTopicCellClicked != null)
            onTopicCellClicked.onMemberClicked(entity.getMember());
    }

    public void onNodeClicked(View view) {
        if (onTopicCellClicked != null)
            onTopicCellClicked.onNodeClicked(entity.getNode());
    }

}
