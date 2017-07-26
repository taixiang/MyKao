package com.kaoyan.api;

import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.model.NovelItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tx on 2017/7/17.
 * 所有接口地址
 */
public interface IApi {


    //pro.ashx?action=novel&page=%s
    @GET("API/pro.ashx?action=novel")
    Observable<NovelItem> getNovel(@Query("page") int page);

    @GET("Handler/List.ashx?action=findBookListAPI&keywords=")
    Observable<FindItem> getFind(@Query("pageIndex")int page);

    @GET("Handler/List.ashx?action=findBookListAPI&keywords=")
    Call<FindItem> getFindItem(@Query("pageIndex")int page);

}
