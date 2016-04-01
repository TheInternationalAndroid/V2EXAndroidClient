package com.rayman.v2ex.viewmodel.test;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.model.model.test.TestRxEntity;
import com.rayman.v2ex.ui.adapter.list.TestRxListAdapter;
import com.rayman.v2ex.ui.view.test.rx.TestRxContract;
import com.rayman.v2ex.viewmodel.BasePVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/30/16
 * Time: 10:25 AM
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
public class TestRxVM extends BasePVM<TestRxContract.Presenter> {

    private final RecyclerView.LayoutManager layoutManager;
    private final TestRxListAdapter adapter;

    public TestRxVM(TestRxContract.Presenter presenter, RecyclerView.LayoutManager layoutManager) {
        super(presenter);
        this.layoutManager = layoutManager;
        adapter = new TestRxListAdapter(getTestList());
    }

    private List<TestRxEntity> getTestList() {
        List<TestRxEntity> list = new ArrayList<>();
        list.add(new TestRxEntity("TestThread", (position, entity) -> presenter.testThread()));
        list.add(new TestRxEntity("TestEmpty", (position, entity) -> presenter.testEmpty()));
        list.add(new TestRxEntity("TestNever", (position, entity) -> presenter.testNever()));
        list.add(new TestRxEntity("TestFrom", (position, entity) -> presenter.testFrom()));
        list.add(new TestRxEntity("TestFromFuture", (position, entity) -> presenter.testFromFuture()));
        list.add(new TestRxEntity("TestInterval", (position, entity) -> presenter.testInterval()));
        list.add(new TestRxEntity("TestJust", (position, entity) -> presenter.testJust()));
        list.add(new TestRxEntity("TestRange", (position, entity) -> presenter.testRange()));
        list.add(new TestRxEntity("TestRepeat", (position, entity) -> presenter.testRepeat()));
        list.add(new TestRxEntity("TestStartWith", (position, entity) -> presenter.testStartWith()));
        return list;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public TestRxListAdapter getAdapter() {
        return adapter;
    }

}
