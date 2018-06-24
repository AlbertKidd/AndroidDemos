package com.demo.kidd.library;

import android.content.Context;

import lombok.Getter;

/**
 * @author Kidd
 */
public class Application extends android.app.Application {

    @Getter
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
    }
}
