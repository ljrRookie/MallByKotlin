package com.kotlin.base.presenter

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
import com.ljr.baselibrary.common.BaseApplication.Companion.context
import com.ljr.baselibrary.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject


/*
    MVP中P层 基类
 */
open class BasePresenter<T: BaseView>{
    /**
     * laterinit :
     *      对于非空类型的属性是必须初始化的。如果我们希望延迟进行初始化，就可以使用lateinit关键字
     */
    lateinit var mView:T

  //Dagger注入，Rx生命周期管理
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>
/*

    @Inject
    lateinit var context:Context
*/
    /*
        检查网络是否可用
     */
   fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}
