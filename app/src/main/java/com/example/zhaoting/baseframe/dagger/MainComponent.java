package com.example.zhaoting.baseframe.dagger;

import com.example.zhaoting.baseframe.views.MainActivity;

import dagger.Component;

/**
 * Created by zhaoting on 16/12/16.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    public void inject(MainActivity activity2);
}
