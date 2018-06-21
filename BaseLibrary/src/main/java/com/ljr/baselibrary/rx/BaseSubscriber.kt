package com.ljr.baselibrary.rx

import com.kotlin.base.rx.BaseException
import com.ljr.baselibrary.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by 林佳荣 on 2018/6/8.
 * Github：https://github.com/ljrRookie
 * Function ： Rx订阅者默认实现
 */
open class BaseSubscriber<T>(val baseView: BaseView) : Subscriber<T>(){
    override fun onNext(t: T) {

    }

    override fun onError(e: Throwable?) {
        baseView.hideLoading()
        if (e is BaseException){
            baseView.onError(e.msg)
        }
    }

    override fun onCompleted() {
        baseView.hideLoading()
    }


}