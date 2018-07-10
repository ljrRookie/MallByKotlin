package com.ljr.goods.injection.module

import com.ljr.goods.service.CategoryService
import com.ljr.goods.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
@Module
class CategoryModule {
    @Provides
    fun providesCategoryService(service: CategoryServiceImpl): CategoryService {
        return service
    }

}