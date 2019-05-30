package com.example.practiceproject2

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class SimpleItemTouchHelperCallback(val mListener: ItemTouchHelperListener): ItemTouchHelper.Callback() {
    override fun isLongPressDragEnabled(): Boolean {
        return super.isLongPressDragEnabled()
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
    }

    override fun getMovementFlags(p0: RecyclerView, p1: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        mListener.onMove(viewHolder.adapterPosition,target.adapterPosition)
        return false
    }



    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
        mListener.swipe(viewHolder.adapterPosition, position)
    }

}