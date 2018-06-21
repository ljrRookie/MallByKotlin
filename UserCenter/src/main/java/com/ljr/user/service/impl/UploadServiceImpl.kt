package com.ljr.user.service.impl

import com.ljr.baselibrary.ext.convert
import com.ljr.user.data.repository.UploadRepository
import com.ljr.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class UploadServiceImpl @Inject constructor():UploadService{
    @Inject
    lateinit var reposittory:UploadRepository

    override fun getUploadToken(): Observable<String> {
        return reposittory.getUploadToken().convert()
    }
}