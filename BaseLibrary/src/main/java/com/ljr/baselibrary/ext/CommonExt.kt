package com.ljr.baselibrary.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kennyc.view.MultiStateView
import com.ljr.baselibrary.R
import com.ljr.baselibrary.data.protocol.BaseResp
import com.ljr.baselibrary.rx.BaseFunc
import com.ljr.baselibrary.rx.BaseFuncBoolean
import com.ljr.baselibrary.rx.BaseSubscriber
import com.ljr.baselibrary.utils.GlideUtils
import com.ljr.baselibrary.widgets.DefaultTextWatcher
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by 林佳荣 on 2018/6/12.
 * Github：https://github.com/ljrRookie
 * Function ：Kotlin 通用扩展
 */
/**
 * 扩展Observable 执行
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>
                              , lifecycleProvider: LifecycleProvider<*>) {
    this.observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribeOn(Schedulers.io())
            .subscribe(subscriber)

}

/**
 * 扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert(): Observable<T>{
    return this.flatMap(BaseFunc())
}
/**
 *  扩展Boolean类型数据转换
 */
fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}
fun View.onClick(listener: View.OnClickListener):View {
    setOnClickListener(listener)
    return this
}

fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}
/*
    扩展Button可用性
 */
fun Button.enable(et: EditText, method: () -> Boolean) {
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}
    /*
    ImageView加载网络图片
 */
    fun ImageView.loadUrl(url: String) {
        GlideUtils.loadUrlImage(context, url, this)
    }
/**
 * 多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


