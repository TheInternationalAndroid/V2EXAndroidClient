package com.rayman.v2ex.model.http.service;

import com.rayman.v2ex.model.model.node.NodeEntity;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Android Studio.
 * ProjectName: V2EXAndroidClient
 * Author:  Lena
 * Date: 3/21/16
 * Time: 3:50 PM
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
public interface NodeService {

    @GET("nodes/show.json")
    Observable<Response<NodeEntity>> nodeByName(@Query(value = "name") String name);

    @GET("nodes/all.json")
    Observable<Response<List<NodeEntity>>> nodes();

}
