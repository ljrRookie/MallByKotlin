package com.ljr.user.data.repository

import com.ljr.baselibrary.data.net.RetrofitFactory
import com.ljr.baselibrary.data.protocol.BaseResp
import com.ljr.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class UploadRepository @Inject constructor(){
    fun getUploadToken():Observable<BaseResp<String>>{
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }
}