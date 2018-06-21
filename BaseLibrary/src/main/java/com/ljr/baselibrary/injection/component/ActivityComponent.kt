package com.ljr.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.ljr.baselibrary.injection.ActivityScope
import com.ljr.baselibrary.injection.module.ActivityModule
import com.ljr.baselibrary.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Component

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(ActivityModule::class,LifecycleProviderModule::class))
interface ActivityComponent {
    fun activity(): Activity
    fun context(): Context

    fun lifecycleProvider():LifecycleProvider<*>
}