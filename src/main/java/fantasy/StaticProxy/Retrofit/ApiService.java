//package com.fantasy.study.StaticProxy.Retrofit;
//
//import java.util.Observable;
//
//public interface ApiService {
//
//    @POST("http://www.baidu.com/login")
//    Observable<User> login(@Query("username") String username, @Query("password") String password);
//
//    @GET("http://www.baidu.com/checkupdate")
//    Observable<CheckUpdate> checkUpdate(@Query("version") String version);
//
//}