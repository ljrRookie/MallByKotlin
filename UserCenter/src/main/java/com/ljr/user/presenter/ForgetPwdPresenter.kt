package com.ljr.user.presenter

import com.ljr.baselibrary.ext.execute
import com.ljr.baselibrary.presenter.BasePresenter
import com.ljr.baselibrary.rx.BaseSubscriber
import com.ljr.user.presenter.view.ForgetPwdView
import com.ljr.user.service.UserService
import javax.inject.Inject

/*
    忘记密码Presenter
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService


    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                if (t)
                    mView.onForgetPwdResult("验证成功")
            }
        }, lifecycleProvider)

    }

}
