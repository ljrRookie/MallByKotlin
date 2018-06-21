package com.ljr.user.ui.activitys

import android.os.Bundle
import android.view.View
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.utils.UserPrefsUtils
import com.ljr.baselibrary.ext.enable
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.presenter.view.BaseView
import com.ljr.baselibrary.ui.activity.BaseMvpActivity
import com.ljr.user.R
import com.ljr.user.injection.component.DaggerUserComponent
import com.ljr.user.injection.module.UserModule
import com.ljr.user.presenter.LoginPresenter
import com.ljr.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by 林佳荣 on 2018/6/19.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class LoginActivity: BaseMvpActivity<LoginPresenter>(),LoginView, View.OnClickListener {
    override fun onClick(v: View) {
        when(v.id){
            R.id.mRightTv ->{ startActivity<RegistActivity>()}
            R.id.mLoginBtn->{
                mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")
            }
            R.id.mForgetPwdTv ->{
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    override fun onLoginResult(result: UserInfo) {
    toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        startActivity<UserInfoActivity>()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        mLoginBtn.enable(mMobileEt,{isBtnEnable()})
        mLoginBtn.enable(mPwdEt,{isBtnEnable()})
        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }
    /*
       判断按钮是否可用
    */
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }
}