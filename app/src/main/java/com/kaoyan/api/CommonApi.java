package com.kaoyan.api;

import com.kaoyan.model.BannerItem;
import com.kaoyan.model.BaseItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.model.LoginItem;
import com.kaoyan.model.TestItem;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by tx on 2017/7/24.
 */

public interface CommonApi {
    @GET("API/Default.ashx?action=default_middle")
    Observable<HomeMiddleItem> getMiddle();

    @FormUrlEncoded
    @POST("API/users.ashx?action=check_user")
    Observable<BaseItem<LoginItem>> login(@Field("user_name")String name,
                               @Field("passwords")String password,
                               @Field("device_token")String token);

    @GET("API/Default.ashx?action=default_middle")
    Call<HomeMiddleItem> getmiddleItem();

    @GET("API/Default.ashx?action=news_side")
    Observable<BannerItem> getBanner();

    @GET("v2/598ab894110000b100515cc6")
    Observable<BaseItem<TestItem>> getTestItem();

    @GET("v2/598ab678110000c700515cc0")
    Observable<TestItem> getTestItem2();

}
