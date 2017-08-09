package com.kaoyan.api;

import com.kaoyan.database.Test;
import com.kaoyan.model.BaseItem;
import com.kaoyan.model.FindItem;
import com.kaoyan.model.HomeMiddleItem;
import com.kaoyan.model.NovelItem;
import com.kaoyan.model.TestItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
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

    @Headers("Content-Type:application/json; charset=utf-8")
    @GET("Handler/List.ashx?action=findBookListAPI&keywords=")
    Observable<BaseItem<List<FindItem.Find>>> getFind(@Query("pageIndex")int page);

    @Headers("token:"+"33333")
    @GET("Handler/List.ashx?action=findBookListAPI&keywords=")
    Call<FindItem> getFindItem(@Query("pageIndex")int page);

}
