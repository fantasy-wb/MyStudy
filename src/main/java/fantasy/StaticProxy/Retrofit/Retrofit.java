//package com.fantasy.study.StaticProxy.Retrofit;
//
//import java.lang.reflect.Proxy;
//import java.util.Observable;
//import java.util.Scanner;
//
//
//
//public class Retrofit {
//
//    public static <T> T newProxy(Class<T> clazz) {
//        return  (T) Proxy.newProxyInstance(clazz.getClassLoader(),
//                new Class[] {clazz}, new RequestHandler());
//    }
//
//    public static void main(String[] args) {
//        ApiService apiService = Retrofit.newProxy(ApiService.class);
//        Observable<CheckUpdate> checkUpdateObservable = apiService.checkUpdate("3.1.0");
//        checkUpdateObservable.subscribeOn(Schedulers.io())
//                .subscribe(checkUpdate -> System.out.println(checkUpdate.toString()),
//                        throwable -> System.out.println(throwable.getMessage()));
//
//        //等待工作线程执行完成
//        Scanner sc = new Scanner(System.in);
//        if (sc.next() != null) {}
//    }
//}