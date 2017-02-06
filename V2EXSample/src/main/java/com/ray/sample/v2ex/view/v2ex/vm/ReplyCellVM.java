/*
 *  Copyright (C) 2015 Rayman Yan
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ray.sample.v2ex.view.v2ex.vm;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ray.mvvm.lib.model.model.reply.ReplyEntity;
import com.ray.mvvm.lib.view.adapter.list.base.CellVM;
import com.ray.sample.v2ex.view.common.v2ex.ReplyCellView;

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

    private ReplyCellView replyCellView;

    public ReplyCellVM(ReplyEntity entity, RecyclerView.ViewHolder viewHolder, ReplyCellView replyCellView) {
        super(entity, viewHolder, replyCellView);
        this.replyCellView = replyCellView;
    }

    public void onAvatarClicked(View view) {
        if (data == null)
            return;
        if (replyCellView != null)
            replyCellView.onAvatarClicked(data.getMember());
    }

}
