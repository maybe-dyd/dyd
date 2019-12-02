package com.example.jxxy.mobileshop_dyd.http.service;

import com.example.jxxy.mobileshop_dyd.http.entity.HttpResult;
import com.example.jxxy.mobileshop_dyd.http.entity.MemberEntity;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
//MemberService接口
public interface MemberService {
    @FormUrlEncoded
    @POST("member/login2")
    Observable<HttpResult<MemberEntity>> login2(
            @Field("input") String input,
            @Field("password") String password

    );


    @FormUrlEncoded
    @POST("member")
    Observable<HttpResult<MemberEntity>> register(
            @Field("uname") String uname,
            @Field("password") String password,
            @Field("email") String email

    );

    @FormUrlEncoded
    @PUT("member/{memberId}")
    Observable<HttpResult> changePassword(
            @Path("memberId") String memberId,
            @Query("old_pwd") String old_pwd,
            @Query("new_pwd") String new_pwd
    );
}//使用的是@Query注解声明参数，@GET("getAppList")声明的是相对地址。
