package com.ljr.goods.service

import com.ljr.goods.data.protocol.Category
import rx.Observable

/**
 * Created by user on 2018/7/9.
 */
interface CategoryService {
    /*
     获取分类
  */
    fun getCategory(parentId:Int): Observable<MutableList<Category>?>
}