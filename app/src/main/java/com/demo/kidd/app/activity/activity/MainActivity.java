package com.demo.kidd.app.activity.activity;

import com.demo.kidd.android.R;
import com.demo.kidd.app.activity.RequestUrlConstant;
import com.demo.kidd.app.activity.api.API;
import com.demo.kidd.library.activity.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadData() {
        request(RequestUrlConstant.LOGIN, "admin", "koala");
    }

    @Override
    protected Observable<ResponseBody> newTask(String url, Object... args) {
        switch (url) {
            case RequestUrlConstant.LOGIN:
                API api = getApi(API.class);
                return api.login((String)args[0], (String)args[1]);
            default:
                break;
        }
        return null;
    }

    @Override
    protected void onRequestNext(Object o, Disposable disposable) {

    }

    @Override
    protected void onRequestError(Throwable e) {

    }

    @Override
    protected void onRequestComplete() {

    }
}
