package com.ljr.user.service

import rx.Observable

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
interface UploadService {
    fun getUploadToken():Observable<String>
}