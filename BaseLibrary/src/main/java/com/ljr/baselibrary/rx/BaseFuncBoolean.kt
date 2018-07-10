package com.ljr.baselibrary.rx

import com.ljr.baselibrary.common.ResultCode
import com.ljr.baselibrary.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by 林佳荣 on 2018/6/15.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class BaseFuncBoolean<T>: Func1<BaseResp<T>,Observable<Boolean>> {
    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if(t.status != ResultCode.SUCCESS){
            return Observable.error(BaseException(t.status,t.message))
           // return Observable.just(false)

        }
        return Observable.just(true)
    }
}