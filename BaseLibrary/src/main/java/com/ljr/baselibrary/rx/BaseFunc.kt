package com.ljr.baselibrary.rx

import com.kotlin.base.common.ResultCode
import com.kotlin.base.rx.BaseException
import com.ljr.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class BaseFunc<T>: Func1<BaseResp<T>,Observable<T>> {
    override fun call(t: BaseResp<T>): Observable<T> {
        if(t.status != ResultCode.SUCCESS){
            return Observable.error(BaseException(t.status,t.message))
        }
        return Observable.just(t.data)
    }
}