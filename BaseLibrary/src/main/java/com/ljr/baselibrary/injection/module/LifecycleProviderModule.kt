package com.ljr.baselibrary.injection.module

import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {
    @Provides
    fun provideLifecycleProvider():LifecycleProvider<*>{
        return lifecycleProvider
    }
}