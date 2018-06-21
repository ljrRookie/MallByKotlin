package com.ljr.user.injection.component

import com.ljr.baselibrary.injection.PerComponentScope
import com.ljr.baselibrary.injection.component.ActivityComponent
import com.ljr.user.injection.module.UploadModule
import com.ljr.user.injection.module.UserModule
import com.ljr.user.ui.activitys.*

import dagger.Component

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(UserModule::class,UploadModule::class))
interface UserComponent {
    fun inject(activity: RegistActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: ForgetPwdActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: UserInfoActivity)
}