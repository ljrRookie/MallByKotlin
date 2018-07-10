package com.ljr.mallbykotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment

import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ljr.baselibrary.ui.activity.BaseActivity
import com.ljr.baselibrary.ui.fragment.BaseFragment
import com.ljr.goods.ui.fragment.CategoryFragment
import com.ljr.mallbykotlin.R
import com.ljr.mallbykotlin.ui.fragment.HomeFragment
import com.ljr.mallbykotlin.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*

class MainActivity : BaseActivity() {
    //Fragment 栈管理
    private val mStack = Stack<BaseFragment>()
    //主界面Fragment
    private val mHomeFragment by lazy { HomeFragment() }
    //商品分类Fragment
    private val mCategoryFragment by lazy { CategoryFragment() }
    //购物车Fragment
    private val mCartFragment by lazy { HomeFragment() }
    //消息Fragment
    private val mMsgFragment by lazy { HomeFragment() }
    //"我的"Fragment
    private val mMeFragment by lazy { MeFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkCartBadge(10)
       /* Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mBottomNavBar.checkMsgBadge(true) })
        Observable.timer(6, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mBottomNavBar.checkCartBadge(5) })*/
        initFragment()
        initBottomNav()
        changeFragment(0)
    }
    /*
      初始化Fragment栈管理
   */
    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()

        manager.add(R.id.mContaier,mHomeFragment)
        manager.add(R.id.mContaier,mCategoryFragment)
        manager.add(R.id.mContaier,mCartFragment)
        manager.add(R.id.mContaier,mMsgFragment)
        manager.add(R.id.mContaier,mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)

    }
    /*
      初始化底部导航切换事件
   */
    private fun initBottomNav(){
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })

        mBottomNavBar.checkMsgBadge(false)
    }

    /*
        切换Tab，切换对应的Fragment
     */
    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack){
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()
    }

}





