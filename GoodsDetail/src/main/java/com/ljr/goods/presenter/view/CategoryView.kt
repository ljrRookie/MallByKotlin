package com.ljr.goods.presenter.view

import com.ljr.baselibrary.presenter.view.BaseView
import com.ljr.goods.data.protocol.Category

/**
 * Created by user on 2018/7/9.
 */
interface CategoryView :BaseView{
    //获取商品分类列表
    fun onGetCategoryResult(result:MutableList<Category>?)
}