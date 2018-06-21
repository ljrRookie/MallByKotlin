package com.ljr.user.ui.activitys

import android.os.Bundle
import com.ljr.baselibrary.ext.enable
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.ui.activity.BaseMvpActivity
import com.ljr.user.R
import com.ljr.user.injection.component.DaggerUserComponent
import com.ljr.user.injection.module.UserModule
import com.ljr.user.presenter.ResetPwdPresenter
import com.ljr.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * Created by 林佳荣 on 2018/6/19.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()

    }

    /*
        初始化视图
     */
    private fun initView() {

        mConfirmBtn.enable(mPwdEt,{isBtnEnable()})
        mConfirmBtn.enable(mPwdConfirmEt,{isBtnEnable()})

        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                toast("密码不一致")
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"),mPwdEt.text.toString())
        }
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        判断按钮是否可用
     */
    private fun isBtnEnable():Boolean{
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    /*
        重置密码回调
     */
    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }
}
