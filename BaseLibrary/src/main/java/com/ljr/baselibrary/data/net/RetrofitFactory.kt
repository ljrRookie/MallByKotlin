package com.ljr.baselibrary.data.net

import com.ljr.baselibrary.common.BaseConstant
import com.ljr.baselibrary.utils.AppPrefsUtils
import com.ljr.baselibrary.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by 林佳荣 on 2018/6/8.
 * Github：https://github.com/ljrRookie
 * Function ： Retrofit工厂，单例
 */
class RetrofitFactory private constructor() {
    /**
     * 单例实现
     */
    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }

    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit
    val LINE_SEPARATOR = System.getProperty("line.separator")

    init {
        //通用拦截
        interceptor = Interceptor {
            chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content_Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .addHeader("token", AppPrefsUtils.getString(BaseConstant.KEY_SP_TOKEN))
                    .build()

            chain.proceed(request)
        }

        //Retrofit实例化
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /*
     OKHttp创建
  */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()
    }

    /*
        日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {

        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(msg: String) {
               LogUtils.printJson("okhttp",msg)


        }
    })
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

/*
    具体服务实例化
 */
fun <T> create(service: Class<T>): T {
    return retrofit.create(service)
}
}