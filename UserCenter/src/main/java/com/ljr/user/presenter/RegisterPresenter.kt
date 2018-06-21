package com.ljr.user.presenter

import com.kotlin.base.presenter.BasePresenter
import com.ljr.baselibrary.ext.execute
import com.ljr.baselibrary.rx.BaseSubscriber
import com.ljr.user.presenter.view.RegisterView
import com.ljr.user.service.UserService
import javax.inject.Inject


/**
 * Created by 林佳荣 on 2018/6/12.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService : UserService
    fun register(mobile: String, pwd: String, veriftCode: String){
        /**
         * 业务逻辑
         */
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.register(mobile,pwd,veriftCode)
                .execute(object : BaseSubscriber<Boolean>(mView){
                    override fun onNext(t: Boolean) {
                        if(t) {
                            mView.onRegisterResult("注册成功")
                        }else{
                            mView.onRegisterResult("注册失败")
                        }
                    }
                },lifecycleProvider)
    }
}