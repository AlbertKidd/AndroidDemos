package com.demo.kidd.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

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
    protected abstract void initVariables(Intent intent);

    /**
     * 当前activity的布局资源id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    /**
     * 加载初始数据
     */
    protected abstract void loadData();
}
