package com.ljr.baselibrary.injection.module

import android.content.Context
import com.ljr.baselibrary.common.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@Module
@Singleton
class AppModule(private val context : BaseApplication) {
    @Provides
    @Singleton
    fun providesContezxt() : Context{
        return context
    }

}