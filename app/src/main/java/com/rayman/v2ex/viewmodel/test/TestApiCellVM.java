package com.rayman.v2ex.viewmodel.test;

import android.view.View;

import com.rayman.v2ex.R;
import com.rayman.v2ex.model.model.TestApiEntity;
import com.rayman.v2ex.ui.adapter.OnItemClick;
import com.rayman.v2ex.widget.anotations.ViewClick;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 5:19 PM
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
public class TestApiCellVM {

    private TestApiEntity entity;
    private OnItemClick<TestApiEntity> onItemClick;
    private int position;

    public TestApiCellVM(TestApiEntity entity, int position, OnItemClick<TestApiEntity> onItemClick) {
        this.entity = entity;
        this.position = position;
        this.onItemClick = onItemClick;
    }

    public TestApiEntity getEntity() {
        return entity;
    }

    public int getPosition() {
        return position;
    }

    @ViewClick(R.id.cell_content)
    public void onContentClicked(View view) {
        if (onItemClick != null)
            onItemClick.onItemClick(position, entity);
    }

}
