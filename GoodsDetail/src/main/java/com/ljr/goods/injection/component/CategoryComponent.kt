package com.ljr.goods.injection.component

import com.ljr.baselibrary.injection.PerComponentScope
import com.ljr.baselibrary.injection.component.ActivityComponent
import com.ljr.goods.injection.module.CategoryModule
import com.ljr.goods.ui.fragment.CategoryFragment


import dagger.Component

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),modules = arrayOf(CategoryModule::class))
interface CategoryComponent {
    fun inject(fragment: CategoryFragment)

}