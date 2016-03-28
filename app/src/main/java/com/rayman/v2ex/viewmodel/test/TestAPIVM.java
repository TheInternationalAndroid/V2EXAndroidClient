package com.rayman.v2ex.viewmodel.test;

import android.support.v7.widget.RecyclerView;

import com.rayman.v2ex.model.http.Host;
import com.rayman.v2ex.model.http.callback.LSubscriber;
import com.rayman.v2ex.model.http.event.ErrorEvent;
import com.rayman.v2ex.model.model.TestApiEntity;
import com.rayman.v2ex.model.model.member.MemberEntity;
import com.rayman.v2ex.model.model.node.NodeEntity;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.presenter.test.ITestApiP;
import com.rayman.v2ex.ui.adapter.list.TestApiListAdapter;
import com.rayman.v2ex.viewmodel.BasePVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 2:39 PM
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
public class TestApiVM extends BasePVM<ITestApiP> {

    private TestApiListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public TestApiVM(ITestApiP presenter, RecyclerView.LayoutManager layoutManager) {
        super(presenter);
        this.layoutManager = layoutManager;
        adapter = new TestApiListAdapter(this::onItemClick);
        initData();
    }

    public void onItemClick(final int position, final TestApiEntity testApiEntity) {
        switch (position) {
            case 0:
                presenter.requestHotList(getTopicListCallback(position, testApiEntity));
                break;
            case 1:
                presenter.requestLastestList(getTopicListCallback(position, testApiEntity));
                break;
            case 2:
                presenter.requestTopicListByName("Livid", getTopicListCallback(position, testApiEntity));
                break;
            case 3:
                presenter.requestTopicListByNodeId(1, getTopicListCallback(position, testApiEntity));
                break;
            case 4:
                presenter.requestTopicListByNodeName("python", getTopicListCallback(position, testApiEntity));
                break;
            case 5:
                presenter.requestTopicById(265124, new LSubscriber<List<TopicEntity>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onSuccess(List<TopicEntity> respEntity) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(ErrorEvent errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            case 6:
                presenter.requestReplies(265124, new LSubscriber<List<ReplyEntity>>() {

                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onSuccess(List<ReplyEntity> respEntity) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(ErrorEvent errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            case 7:
                presenter.requestNode("python", new LSubscriber<NodeEntity>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onSuccess(NodeEntity respEntity) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(ErrorEvent errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            case 8:
                presenter.requestNodelist(new LSubscriber<List<NodeEntity>>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onSuccess(List<NodeEntity> response) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(ErrorEvent errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;

            case 9:
                presenter.requestMember("linpf", new LSubscriber<MemberEntity>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        testApiEntity.setState(TestApiEntity.LOADING);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onSuccess(MemberEntity response) {
                        testApiEntity.setState(TestApiEntity.SUCCESS);
                        adapter.notifyItemChanged(position);
                    }

                    @Override
                    public void onError(ErrorEvent errorEvent) {
                        testApiEntity.setState(TestApiEntity.ERROR);
                        adapter.notifyItemChanged(position);
                    }
                });
                break;
            default:
                break;
        }
    }

    private LSubscriber<List<TopicEntity>> getTopicListCallback(final int position, final TestApiEntity testApiEntity) {
        return new LSubscriber<List<TopicEntity>>() {
            @Override
            public void onStart() {
                super.onStart();
                testApiEntity.setState(TestApiEntity.LOADING);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onSuccess(List<TopicEntity> respEntity) {
                testApiEntity.setState(TestApiEntity.SUCCESS);
                adapter.notifyItemChanged(position);
            }

            @Override
            public void onError(ErrorEvent errorEvent) {
                testApiEntity.setState(TestApiEntity.ERROR);
                adapter.notifyItemChanged(position);
            }
        };
    }

    private void initData() {
        TestApiEntity hotApi = new TestApiEntity(Host.BASE_URL + "topics/hot.json");
        TestApiEntity latestedApi = new TestApiEntity(Host.BASE_URL + "topics/latest.json");
        TestApiEntity topicsByNameApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?username=Livid");
        TestApiEntity topicListByNodeIdApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?node_id=1");
        TestApiEntity topicListByNodeNameApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?node_name=python");
        TestApiEntity topicByIdApi = new TestApiEntity(Host.BASE_URL + "topics/show.json?id=265124");

        TestApiEntity repliesApi = new TestApiEntity(Host.BASE_URL + "replies/show.json?id=265124");

        TestApiEntity nodeByNamApi = new TestApiEntity(Host.BASE_URL + "nodes/show.json?name=python");
        TestApiEntity nodeListApi = new TestApiEntity(Host.BASE_URL + "nodes/all.json");

        TestApiEntity member = new TestApiEntity(Host.BASE_URL + "members/show.json?username=linpf");

        List<TestApiEntity> apiEntities = new ArrayList<>();
        apiEntities.add(hotApi);
        apiEntities.add(latestedApi);
        apiEntities.add(topicsByNameApi);
        apiEntities.add(topicListByNodeIdApi);
        apiEntities.add(topicListByNodeNameApi);
        apiEntities.add(topicByIdApi);

        apiEntities.add(repliesApi);

        apiEntities.add(nodeByNamApi);
        apiEntities.add(nodeListApi);

        apiEntities.add(member);

        adapter.setList(apiEntities);
    }

    public TestApiListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

}
