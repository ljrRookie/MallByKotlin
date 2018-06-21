package com.ljr.user.injection.module

import com.ljr.user.service.UserService
import com.ljr.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@Module
class UserModule {
    @Provides
    fun providesUserService(service:UserServiceImpl):UserService{
        return service
    }

}