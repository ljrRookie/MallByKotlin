package com.ljr.user.service.impl

import android.util.Log

import com.ljr.baselibrary.data.protocol.BaseResp
import com.ljr.baselibrary.ext.convert
import com.ljr.baselibrary.ext.convertBoolean
import com.ljr.baselibrary.rx.BaseFuncBoolean
import com.ljr.user.data.protocol.UserInfo
import com.ljr.user.data.repository.UserRepository
import com.ljr.user.service.UserService
import rx.Observable
import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by 林佳荣 on 2018/6/12.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class UserServiceImpl @Inject constructor():UserService {

    @Inject
    lateinit var repository :UserRepository
    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return repository.register(mobile,pwd,verifyCode)
                .convertBoolean()
    }
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return repository.login(mobile, pwd, pushId).convert()

    }
    /*
           忘记密码
        */
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return repository.forgetPwd(mobile, verifyCode).convertBoolean()
    }

    /*
        重置密码
     */
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return repository.resetPwd(mobile, pwd).convertBoolean()
    }

    /*
        修改用户资料
     */
    override fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<UserInfo> {
        return repository.editUser(userIcon,userName,userGender,userSign).convert()
    }

}