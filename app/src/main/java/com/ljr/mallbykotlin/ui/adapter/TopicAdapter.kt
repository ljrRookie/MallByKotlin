package com.ljr.mallbykotlin.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljr.baselibrary.ext.loadUrl
import com.ljr.mallbykotlin.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * Created by 林佳荣 on 2018/6/20.
 * Github：https://github.com/ljrRookie
 * Function ：
 */
class TopicAdapter (private val context: Context, private val list: List<String>) : PagerAdapter() {
    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null)
        rooView.mTopicIv.loadUrl(list[position])
        parent.addView(rooView)
        return rooView
    }

    override fun destroyItem(parent: ViewGroup, paramInt: Int, paramObject: Any) {
        parent.removeView(paramObject as View)
    }

    override fun isViewFromObject(view: View,paramObject: Any): Boolean {
        return view === paramObject

    }

    override fun getCount(): Int {
        return this.list.size    }
}