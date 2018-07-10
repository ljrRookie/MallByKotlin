package com.ljr.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljr.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.ljr.goods.R
import com.ljr.goods.data.protocol.Category
import kotlinx.android.synthetic.main.fragment_category.view.*
import kotlinx.android.synthetic.main.layout_top_category_item.view.*

/**
 * Created by user on 2018/7/10.
 */
class TopCategoryAdapter(context:Context):BaseRecyclerViewAdapter<Category,TopCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //分类名称
        holder.itemView.mTopCategoryNameTv.text = model.categoryName
        //是否被选中
        holder.itemView.mTopCategoryNameTv.isSelected = model.isSelected
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}