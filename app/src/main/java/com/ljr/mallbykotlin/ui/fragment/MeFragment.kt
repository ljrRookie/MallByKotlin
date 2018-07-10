package com.ljr.mallbykotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljr.baselibrary.ext.loadUrl
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.ui.fragment.BaseFragment
import com.ljr.baselibrary.utils.AppPrefsUtils
import com.ljr.mallbykotlin.R
import com.ljr.mallbykotlin.ui.activity.SettingActivity
import com.ljr.provider.common.ProviderConstant
import com.ljr.provider.common.afterLogin
import com.ljr.provider.common.isLogined
import com.ljr.user.ui.activitys.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by user on 2018/7/9.
 */
class MeFragment : BaseFragment(), View.OnClickListener{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me,null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /**
     * 加载初始数据
     */
    private fun loadData() {
        if(isLogined()){
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if(userIcon.isNotEmpty()){
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        }else{
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    private fun initView() {
        mUserIconIv.onClick (this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.mUserIconIv,R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }
            R.id.mAllOrderTv -> {
                afterLogin {
                  //  startActivity<OrderActivity>()
                }
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }


}