package com.example.practiceproject2.ui.home

import android.os.Handler
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceproject2.EndlessScrolledRecyclerViewListener
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

    override fun getViewId(): Int {
        return R.layout.fragment_home
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun initData() {
        //Replace Call API
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
            initData()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        mAdapter = HomeAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            addOnScrollListener(object : EndlessScrolledRecyclerViewListener(){
                override fun onLoadMore() {
                    if (mDataList.size < 50) {
                        mDataList.add(null)
                        mAdapter.notifyItemInserted(mDataList.size - 1)
                        Handler().postDelayed({
                            mDataList.removeAt(mDataList.size -1 )
                            mAdapter.notifyItemRemoved(mDataList.size )

                            //more data
                            val index = mDataList.size
                            val end = index + 10
                            for (i in index until end ) {
                                mAdapter.addData(UUID.randomUUID().toString())
                            }
                            updateLoading()
                        },500)
                    }
                }
            })
        }
        mAdapter.setDataList(mDataList)
    }
}