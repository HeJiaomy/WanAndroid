package com.shehuan.wanandroid.ui.tree.treeDetail

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout
import com.shehuan.wanandroid.R
import com.shehuan.wanandroid.adapter.ViewPagerAdapter
import com.shehuan.wanandroid.base.activity.BaseActivity
import com.shehuan.wanandroid.base.fragment.BaseFragment
import com.shehuan.wanandroid.bean.tree.ChildrenItem
import kotlinx.android.synthetic.main.activity_tree_detail.*

class TreeDetailActivity : BaseActivity() {
    private lateinit var secondaryTree: ArrayList<ChildrenItem>

    companion object {
        fun start(context: Context, secondaryTree: ArrayList<ChildrenItem>) {
            val intent = Intent(context, TreeDetailActivity::class.java)
            intent.putParcelableArrayListExtra("secondaryTree", secondaryTree)
            context.startActivity(intent)
        }
    }

    override fun initLayoutResID(): Int {
        return R.layout.activity_tree_detail
    }

    override fun initData() {
        secondaryTree = intent.getParcelableArrayListExtra("secondaryTree")
    }

    override fun initView() {
        val titles = arrayListOf<String>()
        val fragments = arrayListOf<BaseFragment>()
        for (tree in secondaryTree) {
            titles.add(tree.name)
            fragments.add(TreeDetailFragment.newInstance(tree.id))
        }

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.setFragmentsAndTitles(fragments, titles)
        treeDetailViewPager.offscreenPageLimit = secondaryTree.size
        treeDetailViewPager.adapter = viewPagerAdapter
        if (titles.size>3){
            treeDetailTabLayout.tabMode = TabLayout.MODE_SCROLLABLE
        }
        treeDetailTabLayout.setupWithViewPager(treeDetailViewPager)
    }
}
