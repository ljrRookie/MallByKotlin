package com.ljr.user.ui.activitys

import android.os.Bundle
import android.view.View
import com.ljr.baselibrary.ext.enable
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.ui.activity.BaseMvpActivity

import com.ljr.user.R
import com.ljr.user.injection.component.DaggerUserComponent
import com.ljr.user.injection.module.UserModule
import com.ljr.user.presenter.RegisterPresenter
import com.ljr.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_regist.*
import org.jetbrains.anko.toast

class RegistActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView,View.OnClickListener{
    override fun injectComponent() {
        //mPresenter = new RegistPresenter()
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun onRegisterResult(resule: String) {
        toast(resule)
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist)
        initView()
        mRegisterBtn.onClick{
            mPresenter.register(mMobileEt.text.toString(),mPwdEt.text.toString(),mVerifyCodeEt.text.toString())
        }
    }

    private fun initView() {
        mRegisterBtn.enable(mMobileEt,{isBtnEnable()})
        mRegisterBtn.enable(mVerifyCodeEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdEt,{isBtnEnable()})
        mRegisterBtn.enable(mPwdConfirmEt,{isBtnEnable()})
        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }
    /*
          点击事件
       */
    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }

            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(),mPwdEt.text.toString(),mVerifyCodeEt.text.toString())
            }

        }
    }
    /*
          判断按钮是否可用
       */
    private fun isBtnEnable():Boolean{
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()&&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}

