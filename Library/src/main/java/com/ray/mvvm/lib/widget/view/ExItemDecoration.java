/*
 *
 *  Copyright (c) 2016 Lena.t.Yan
 *  Unauthorized copying of this file, via any medium is strictly prohibited proprietary and confidential.
 *  Created on Sun, 9 Oct 2016 00:21:32 +0800.
 *  ProjectName: V2EXAndroidClient ; ModuleName: app ; ClassName: TopicListCellVM.
 *  Author: Lena; Last Modified: Sun, 9 Oct 2016 00:21:32 +0800.
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

package com.ray.mvvm.lib.widget.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ExItemDecoration extends RecyclerView.ItemDecoration {

    private int dividerheight;
    private boolean hideFirstItemOffset = false;
    private boolean showLastItemOffset = false;

    private int paddingLeft;
    private int paddingRight;
    private Paint paint;

    public ExItemDecoration(int dividerheight, boolean hideFirstItemOffset, boolean showLastItemOffset, int paddingLeft, int paddingRight, @ColorInt int dividerColor) {
        this.dividerheight = dividerheight;
        this.hideFirstItemOffset = hideFirstItemOffset;
        this.showLastItemOffset = showLastItemOffset;
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
        paint = new Paint();
        paint.setColor(dividerColor);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int itemCount = parent.getAdapter().getItemCount();
        final int itemPosition = parent.getChildAdapterPosition(view);
        if ((!showLastItemOffset && itemPosition >= itemCount - 1) || (hideFirstItemOffset && itemPosition == 0)) {
            return;
        }
        outRect.set(0, 0, 0, dividerheight);
        Canvas canvas = new Canvas();
        canvas.drawRect(outRect, paint);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        final int itemCount = parent.getAdapter().getItemCount();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if ((!showLastItemOffset && i >= itemCount - 1) || (hideFirstItemOffset && i == 0)) {
                continue;
            }
            View child = parent.getChildAt(i);
            c.drawRect(
                    layoutManager.getDecoratedLeft(child) + paddingLeft,
                    layoutManager.getDecoratedBottom(child) - dividerheight,
                    layoutManager.getDecoratedRight(child) - paddingRight,
                    layoutManager.getDecoratedBottom(child),
                    paint
            );
        }
    }

}
