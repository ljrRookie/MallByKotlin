package com.l

import com.kotlin.user.data.protocol.UserInfo
import com.ljr.baselibrary.rx.BaseSubscriber
import com.kotlin.base.presenter.BasePresenter
import com.ljr.baselibrary.ext.execute
import com.ljr.user.presenter.view.UserInfoView
import com.ljr.user.service.UploadService
import com.ljr.user.service.UserService
import javax.inject.Inject

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：编辑用户资料
 */
class UserInfoPresenter @Inject constructor() :BasePresenter<UserInfoView>(){
    @Inject
    lateinit var userService: UserService
    @Inject
    lateinit var uploadService: UploadService
    /**
     * 获取七牛云上传凭证
     */
    fun getUploadToken(){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        uploadService.getUploadToken().execute(object :BaseSubscriber<String>(mView){
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }
    /**
     * 编辑用户资料
     */
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.editUser(userIcon,userName,userGender,userSign).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        },lifecycleProvider)
    }
}