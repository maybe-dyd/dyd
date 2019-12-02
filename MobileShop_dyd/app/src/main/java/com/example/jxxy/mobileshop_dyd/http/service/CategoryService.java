package com.example.jxxy.mobileshop_dyd.http.service;

import com.example.jxxy.mobileshop_dyd.http.entity.CategoryEntity;
import com.example.jxxy.mobileshop_dyd.http.entity.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
//CategoryService接口
public interface CategoryService {

    @GET("cat")
    Observable<HttpResult<List<CategoryEntity>>> getTopList();

    @GET("cat/parent/{parentId}")
    Observable<HttpResult<List<CategoryEntity>>> getSecondList(@Path("parentId") int parentId);

}
