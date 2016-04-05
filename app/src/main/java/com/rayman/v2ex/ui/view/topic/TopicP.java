package com.rayman.v2ex.ui.view.topic;

import com.rayman.v2ex.model.http.service.ReplyService;
import com.rayman.v2ex.model.http.service.TopicService;
import com.rayman.v2ex.model.model.reply.ReplyEntity;
import com.rayman.v2ex.model.model.topic.TopicEntity;
import com.rayman.v2ex.ui.view.base.presenter.BasePresenter;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 4/5/16
 * Time: 3:33 PM
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
public class TopicP extends BasePresenter implements TopicContract.Preenter {

    private TopicService topicService;
    private ReplyService replyService;

    @Inject
    public TopicP(RefWatcher refWatcher, TopicService topicService, ReplyService replyService) {
        super(refWatcher);
        this.topicService = topicService;
        this.replyService = replyService;
    }

    @Override
    public void requestDetail(long id, Subscriber<List<TopicEntity>> subscriber) {
        subscribeHttpReq(topicService.topicById(id), subscriber);
    }

    @Override
    public void requestReplies(long id, Subscriber<List<ReplyEntity>> subscriber) {
        subscribeHttpReq(replyService.replies(id), subscriber);
    }
}
