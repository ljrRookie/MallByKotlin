package com.ljr.baselibrary.data.protocol

/**
 * Created by 林佳荣 on 2018/6/14.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class BaseResp<out T>(val status : Int,val message:String,val data :T) {
}