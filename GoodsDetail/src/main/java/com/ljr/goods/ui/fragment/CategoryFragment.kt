package com.ljr.goods.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kennyc.view.MultiStateView
import com.ljr.baselibrary.ext.setVisible
import com.ljr.baselibrary.ext.startLoading
import com.ljr.baselibrary.ui.activity.BaseMvpFragment
import com.ljr.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.ljr.goods.R
import com.ljr.goods.data.protocol.Category
import com.ljr.goods.injection.component.DaggerCategoryComponent
import com.ljr.goods.injection.module.CategoryModule
import com.ljr.goods.presenter.CategoryPresenter
import com.ljr.goods.presenter.view.CategoryView
import com.ljr.goods.ui.adapter.SecondCategoryAdapter
import com.ljr.goods.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*


/**
 * Created by user on 2018/7/9.
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {

    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(activityComponent).categoryModule(CategoryModule())
                .build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }

        mPresenter.getCategory(parentId)

    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (categroy in topAdapter.dataList) {
                    categroy.isSelected = item.id == categroy.id
                }
                topAdapter.notifyDataSetChanged()
                loadData(item.id)
            }
        })
        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                //startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID  to item.id)
            }
        })
    }

    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}