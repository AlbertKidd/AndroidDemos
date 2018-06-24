package com.demo.kidd.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.kidd.library.manager.RetrofitManager;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Kidd
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables(getIntent());
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initViews();
        loadData();
    }

    /**
     * 初始化Intent传入的数据
     */
    protected void initVariables(Intent intent){}

    /**
     * 当前activity的布局资源id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected void initViews(){}

    /**
     * 加载初始数据
     */
    protected abstract void loadData();

    protected abstract <T> Observable<T> newTask(String url, Object... args);

    protected abstract void onRequestNext(Object o, Disposable disposable);

    protected abstract void onRequestError(Throwable e);

    protected abstract void onRequestComplete();

    protected <T> T getApi(Class<T> clz){
        return RetrofitManager.getRetrofit().create(clz);
    }

    protected void request(String url, Object... args){
        Observable observable = newTask(url, args);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(Object o) {
                        onRequestNext(o, disposable);
                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestError(e);
                    }

                    @Override
                    public void onComplete() {
                        onRequestComplete();
                    }
                });
    }

    private void test(){
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("something");
            }
        }).subscribeOn(Schedulers.newThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<Object>() {
                private Disposable mDisposable;

                @Override
                public void onSubscribe(Disposable d) {
                    mDisposable = d;
                }

                @Override
                public void onNext(Object o) {
                    if (o.equals("something")){
                        mDisposable.dispose();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
                }

                @Override
                public void onComplete() {
                    Log.i(this.getClass().getSimpleName(), "success");
                }
        });
    }
}
