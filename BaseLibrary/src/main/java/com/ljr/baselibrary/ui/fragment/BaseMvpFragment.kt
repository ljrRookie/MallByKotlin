package com.ljr.baselibrary.ui.activity

import android.os.Bundle
import com.kotlin.base.presenter.BasePresenter
import com.ljr.baselibrary.common.BaseApplication
import com.ljr.baselibrary.injection.component.ActivityComponent
import com.ljr.baselibrary.injection.component.DaggerActivityComponent
import com.ljr.baselibrary.injection.module.ActivityModule
import com.ljr.baselibrary.injection.module.LifecycleProviderModule
import com.ljr.baselibrary.presenter.view.BaseView
import com.ljr.baselibrary.ui.fragment.BaseFragment
import javax.inject.Inject

/**
 * Created by 林佳荣 on 2018/6/6.
 * Github：https://github.com/ljrRookie
 * Function ：Activity基类，业务相关
 */
abstract open class BaseMvpFragment<T :BasePresenter<*>>:BaseFragment(),BaseView {
    @Inject
    lateinit var mPresenter: T
lateinit  var activityComponent : ActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }
    /*
         Dagger注册
      */
    protected abstract fun injectComponent()
    /*
           初始Activity Component
        */
    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((activity.application as BaseApplication).appComponent)
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .activityModule(ActivityModule(activity)).build()
    }
}