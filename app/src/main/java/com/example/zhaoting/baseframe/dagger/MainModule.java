package com.example.zhaoting.baseframe.dagger;

import com.example.zhaoting.baseframe.views.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by zhaoting on 16/12/16.
 */

@Module
public class MainModule {
    private final BaseView mView;
    public MainModule(BaseView view){
        mView=view;
    }
    @Provides
    BaseView provideBaseView(){
        return mView;
    }
}
