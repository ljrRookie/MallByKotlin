package com.ljr.user.presenter

import com.ljr.user.data.protocol.UserInfo
import com.ljr.baselibrary.ext.execute
import com.ljr.baselibrary.presenter.BasePresenter
import com.ljr.baselibrary.rx.BaseSubscriber
import com.ljr.user.presenter.view.LoginView
import com.ljr.user.service.UserService
import javax.inject.Inject


/**
 * Created by 林佳荣 on 2018/6/12.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {
    @Inject
    lateinit var userService : UserService
    fun login(mobile: String, pwd: String, pushId: String){
        /**
         * 业务逻辑
         */
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
       userService.login(mobile,pwd,pushId).execute(object : BaseSubscriber<UserInfo>(mView){
           override fun onNext(t: UserInfo) {
               mView.onLoginResult(t)
           }
       },lifecycleProvider)
    }
}