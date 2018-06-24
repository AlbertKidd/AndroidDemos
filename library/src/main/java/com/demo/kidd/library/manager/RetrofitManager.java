package com.demo.kidd.library.manager;

import com.demo.kidd.library.Application;
import com.demo.kidd.library.BuildConfig;
import com.demo.kidd.library.util.SharedPreferencesUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Kidd
 */
public class RetrofitManager {

    private Retrofit retrofit;

    private static class InstanceHolder{
        static RetrofitManager manager = new RetrofitManager();

        static void reset(){
            manager = new RetrofitManager();
        }
    }

    private RetrofitManager(){
        init();
    }

    private void init(){
        String baseUrl = SharedPreferencesUtil.getInstance(Application.getContext()).getPreferences().
                getString(SharedPreferencesUtil.KEY_BASE_URL, BuildConfig.BASE_URL);
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public void reset(){
        InstanceHolder.reset();
    }

    public static Retrofit getRetrofit(){
        return InstanceHolder.manager.retrofit;
    }
}
