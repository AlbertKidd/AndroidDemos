package com.demo.kidd.app.activity.api;

import com.demo.kidd.app.activity.RequestUrlConstant;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Kidd on 2018/6/24.
 */
public interface API {

    @POST(RequestUrlConstant.LOGIN)
    Observable<ResponseBody> login(@Header("username")String username, @Header("password")String password);

    @POST("/{serviceId}")
    Observable<ResponseBody> callService(@Path("serviceId")String serviceId);
}
