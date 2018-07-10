package com.ljr.user.presenter.view

import com.ljr.user.data.protocol.UserInfo
import com.ljr.baselibrary.presenter.view.BaseView

/**
 * Created by 林佳荣 on 2018/6/12.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
interface LoginView :BaseView{
fun onLoginResult(result:UserInfo)
}