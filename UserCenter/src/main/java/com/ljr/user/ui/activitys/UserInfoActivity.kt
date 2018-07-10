package com.ljr.user.ui.activitys

import android.os.Bundle
import com.jph.takephoto.model.TResult
import com.ljr.baselibrary.common.BaseConstant
import com.ljr.baselibrary.utils.AppPrefsUtils
import com.ljr.user.utils.UserPrefsUtils
import com.ljr.baselibrary.ext.onClick
import com.ljr.baselibrary.ui.activity.BaseTakePhotoActivity
import com.ljr.baselibrary.utils.GlideUtils
import com.ljr.provider.common.ProviderConstant
import com.ljr.user.R
import com.ljr.user.data.protocol.UserInfo
import com.ljr.user.injection.component.DaggerUserComponent
import com.ljr.user.injection.module.UserModule
import com.ljr.user.presenter.UserInfoPresenter
import com.ljr.user.presenter.view.UserInfoView
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {
    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    private val mUploadManager: UploadManager by lazy { UploadManager() }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, { _, _, response ->
            mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")
            GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
        }, null)
    }

    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initView()
        initData()
    }

    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile
        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }

    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }
        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!, mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: "")
        }
    }

    /**
     * 获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

}