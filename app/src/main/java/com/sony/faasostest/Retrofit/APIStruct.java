package com.sony.faasostest.Retrofit;

import com.sony.faasostest.Model.UserInfo;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by Dell on 25-07-2018.
 */

public interface APIStruct {
    @GET("users")
    Flowable<List<UserInfo>> getInfo();


}
