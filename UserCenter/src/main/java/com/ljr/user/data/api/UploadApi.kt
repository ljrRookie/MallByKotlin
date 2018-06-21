package com.ljr.user.data.api

import com.ljr.baselibrary.data.protocol.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
interface UploadApi {
    /*
       获取七牛云上传凭证
    */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}