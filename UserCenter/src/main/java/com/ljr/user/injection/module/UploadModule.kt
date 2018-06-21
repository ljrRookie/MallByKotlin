package com.ljr.user.injection.module

import com.ljr.user.service.UploadService
import com.ljr.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@Module
class UploadModule {
    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }
}