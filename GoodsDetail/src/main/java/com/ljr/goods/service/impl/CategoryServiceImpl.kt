package com.ljr.goods.service.impl

import com.ljr.baselibrary.ext.convert
import com.ljr.goods.data.protocol.Category
import com.ljr.goods.service.CategoryService
import com.ljr.goodscenter.data.repository.CategoryRepository
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/7/9.
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {
    @Inject
    lateinit var repository: CategoryRepository

    override fun getCategory(parentId: Int): Observable<MutableList<Category>?> {
       return repository.getCategory(parentId).convert()
    }

}