package com.ljr.mallbykotlin.ui.activity

import android.os.Bundle
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.ui.activity.BaseActivity
import com.ljr.mallbykotlin.R
import com.ljr.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

/**
 * Created by user on 2018/7/9.
 */
class SettingActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.onClick {
            toast("用户协议")
        }
        mFeedBackTv.onClick {
            toast("反馈意见")
        }
        mAboutTv.onClick {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}