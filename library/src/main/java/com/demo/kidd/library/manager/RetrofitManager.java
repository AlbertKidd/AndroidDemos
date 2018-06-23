package com.demo.kidd.library.manager;

import retrofit2.Retrofit;

/**
 * @author Kidd
 */
public class RetrofitManager {

    private static class InstanceHolder{
        static RetrofitManager manager = new RetrofitManager();

        static void reset(){
            manager = new RetrofitManager();
        }
    }

    private RetrofitManager(){
        String baseUrl = "";
        new Retrofit.Builder().baseUrl(baseUrl);
    }

    private void init(){

    }

    public static Retrofit getRetrofit(){
        return null;
    }

    public void reset(){
        InstanceHolder.reset();
    }
}
