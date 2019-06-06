package com.example.practiceproject2

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrolledRecyclerViewListener: RecyclerView.OnScrollListener(){
    private var isLoading = false
    private val visivleThresold = 5
    private var mPreviousTotalItemCount = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        val totalItemCount = layoutManager?.itemCount
        val lastVisibleItem = layoutManager?.findLastVisibleItemPosition()
        if (totalItemCount!! < mPreviousTotalItemCount) {
            mPreviousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                isLoading = true
            }
        }
        if (!isLoading && (totalItemCount!! < (lastVisibleItem!! + visivleThresold))) {
            onLoadMore()
            isLoading = true
        }
    }

    fun updateLoading() {
        isLoading = false
    }

    abstract fun onLoadMore()
}