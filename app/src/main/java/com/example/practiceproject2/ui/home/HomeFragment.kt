package com.example.practiceproject2.ui.home

import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.practiceproject2.ItemTouchHelperListener
import com.example.practiceproject2.R
import com.example.practiceproject2.SimpleItemTouchHelperCallback
import com.example.practiceproject2.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment: BaseFragment(){


    private lateinit var mAdapter: HomeAdapter
    private var mDataList: ArrayList<String?> = ArrayList()
    private var isLoading = false
    private var visivleThresold = 5

    override fun getViewId(): Int {
        return R.layout.fragment_home
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initData() {
        for (i in 1..10) {
            mDataList.add(UUID.randomUUID().toString())
        }
    }

    override fun initView() {
        setupRecyclerView()
        val callback = SimpleItemTouchHelperCallback(object : ItemTouchHelperListener{
            override fun onMove(oldPosition: Int, newPosition: Int) {
                mAdapter.onMove(oldPosition,newPosition)
            }

            override fun swipe(position: Int, direction: Int) {
                mAdapter.swipe(position,direction)
            }
        })

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        swipeRefresh.setOnRefreshListener {
            mAdapter.clearDataList()
            mAdapter.addDataList(mDataList)
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        mAdapter = HomeAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    val totalItemCount = layoutManager?.itemCount
                    val lastVisibleItem = layoutManager?.findLastVisibleItemPosition()
                    if (!isLoading && (totalItemCount!! < (lastVisibleItem!! + visivleThresold))) {
                        onLoadMore()
                    }
                }
            })
        }
        mAdapter.addDataList(mDataList)
    }

    fun onLoadMore() {
        if (mDataList.size < 50) {
            mDataList.add(null)
            mAdapter.notifyItemInserted(mDataList.size - 1)
            Handler().postDelayed({
                mDataList.removeAt(mDataList.size -1 )
                mAdapter.notifyItemRemoved(mDataList.size)

                //more data
                val index = mDataList.size
                val end = index + 10
                for (i in index until end ) {
                    mDataList.add(UUID.randomUUID().toString())
                }

                mAdapter.notifyDataSetChanged()
                isLoading = false

            },2000)
        }
    }
}