package com.ljr.user.data.api

import com.kotlin.user.data.protocol.*
import com.ljr.baselibrary.data.protocol.BaseResp
import com.ljr.user.data.protocol.RegisterReq
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by 林佳荣 on 2018/6/14.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
interface UserApi {
    @POST("userCenter/register")
    fun register(@Body req: RegisterReq):Observable<BaseResp<String>>
    /*
      用户登录
   */
    @POST("userCenter/login")
    fun login(@Body req: LoginReq):Observable<BaseResp<UserInfo>>

    /*
        忘记密码
     */
    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body req: ForgetPwdReq):Observable<BaseResp<String>>

    /*
        重置密码
     */
    @POST("userCenter/resetPwd")
    fun resetPwd(@Body req: ResetPwdReq):Observable<BaseResp<String>>

    /*
        编辑用户资料
     */
    @POST("userCenter/editUser")
    fun editUser(@Body req: EditUserReq):Observable<BaseResp<UserInfo>>
}