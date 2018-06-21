package com.ljr.baselibrary.injection.module

import android.app.Activity
import android.content.Context
import com.ljr.baselibrary.common.BaseApplication
import com.ljr.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@Module
class ActivityModule(private val activity : Activity) {
    @ActivityScope
    @Provides
    fun providesActivity() :Activity{
        return activity
    }

}