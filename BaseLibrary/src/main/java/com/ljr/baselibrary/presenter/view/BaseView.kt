package com.ljr.baselibrary.presenter.view

/**
 * Created by 林佳荣 on 2018/6/6.
 * Github：https://github.com/ljrRookie
 * Function ：MVP中视图回调 基类
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}