package com.rayman.v2ex.model.worker;

import com.rayman.v2ex.model.http.callback.ReqCallback;
import com.rayman.v2ex.model.http.service.ReplyService;
import com.rayman.v2ex.model.model.reply.ReplyEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 10:29 PM
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
public class ReplyWorker extends BaseWorker {

    private ReplyService replyService;

    @Inject
    public ReplyWorker(ReplyService replyService) {

        this.replyService = replyService;
    }


    public void replies(long topicId, ReqCallback<List<ReplyEntity>> callback) {
        defaultCall(replyService.replies(topicId), callback);
    }

}
