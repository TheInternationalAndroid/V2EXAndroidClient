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
import com.ray.mvvm.lib.widget.anotations.ViewClick;
import com.rayman.v2ex.R;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.ui.view.common.IMemberCellView;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 9:19 PM
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
public class ReplyCellVM extends CellVM<ReplyEntity> {

    private IMemberCellView onMemberClicked;

    public ReplyCellVM(ReplyEntity entity, int position, IMemberCellView onMemberClicked) {
        super(entity, position);
        this.onMemberClicked = onMemberClicked;
    }

    @ViewClick(R.id.clickable_view)
    public void onAvatarClicked(View view) {
        if (entity == null)
            return;
        if (onMemberClicked != null)
            onMemberClicked.onMemberClicked(entity.getMember());
    }

}