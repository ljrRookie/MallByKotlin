package com.ljr.user.presenter.view

import com.ljr.baselibrary.presenter.view.BaseView

/*
    忘记密码 视图回调
 */
interface ForgetPwdView : BaseView {

    /*
        忘记密码回调
     */
    fun onForgetPwdResult(result:String)
}
