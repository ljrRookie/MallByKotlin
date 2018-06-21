package com.ljr.user.ui.activitys

import android.os.Bundle
import android.view.View

import com.ljr.baselibrary.ext.enable
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.ui.activity.BaseMvpActivity
import com.ljr.user.R
import com.ljr.user.injection.component.DaggerUserComponent
import com.ljr.user.injection.module.UserModule
import com.ljr.user.presenter.ForgetPwdPresenter
import com.ljr.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by 林佳荣 on 2018/6/19.
 * Github：https://github.com/ljrRookie
 * Function ：忘记密码界面
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()

    }

    private fun initView() {
      mNextBtn.enable(mMobileEt,{isBtnEnable()})
        mNextBtn.enable(mVerifyCodeEt,{isBtnEnable()})

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)

    }
    /*
         判断按钮是否可用
      */
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }
    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile"  to mMobileEt.text.toString())
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }
            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }
}