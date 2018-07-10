package com.ljr.goodscenter.data.repository



import com.ljr.baselibrary.data.net.RetrofitFactory
import com.ljr.baselibrary.data.protocol.BaseResp
import com.ljr.goods.data.api.CategoryApi
import com.ljr.goods.data.protocol.Category
import com.ljr.goods.data.protocol.GetCategoryReq

import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/7/9.
 */
class CategoryRepository @Inject constructor(){
    fun getCategory(parentId:Int): Observable<BaseResp<MutableList<Category>?>>{
        return RetrofitFactory.instance.create(CategoryApi::class.java).getCategory(GetCategoryReq(parentId))
    }
}