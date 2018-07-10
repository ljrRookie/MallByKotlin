package com.ljr.goods.presenter

import com.ljr.baselibrary.ext.execute
import com.ljr.baselibrary.presenter.BasePresenter
import com.ljr.baselibrary.rx.BaseSubscriber
import com.ljr.goods.data.protocol.Category
import com.ljr.goods.presenter.view.CategoryView
import com.ljr.goods.service.CategoryService

import rx.functions.Func1
import javax.inject.Inject

/**
 * Created by user on 2018/7/9.
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {
    @Inject
    lateinit var categoryService: CategoryService
    /**
     * 获取商品分类列表
     */
    fun getCategory(parentId: Int){
        if(!checkNetWork()){
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId).execute(object : BaseSubscriber<MutableList<Category>?>(mView){
            override fun onNext(t: MutableList<Category>?) {
                mView.onGetCategoryResult(t)
            }
        },lifecycleProvider)
    }
}