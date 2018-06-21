package com.ljr.baselibrary.common

import android.app.Application
import android.content.Context
import com.ljr.baselibrary.injection.component.AppComponent
import com.ljr.baselibrary.injection.component.DaggerAppComponent
import com.ljr.baselibrary.injection.module.AppModule

/**
 * Created by 林佳荣 on 2018/6/8.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class BaseApplication :Application(){
     lateinit var appComponent : AppComponent
    override fun onCreate() {
        super.onCreate()
        context = this
        initAppInjection()
    }

    private fun initAppInjection() {
         appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    /*
      全局伴生对象
   */
    companion object {
        lateinit var context: Context
    }
}