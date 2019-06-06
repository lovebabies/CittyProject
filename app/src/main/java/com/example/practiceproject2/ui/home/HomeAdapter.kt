package com.example.practiceproject2.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceproject2.R
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.android.synthetic.main.item_loading.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_LOADING = 1
    val VIEW_TYPE_ITEM = 0

    private var mDataList = ArrayList<String?>()

    private lateinit var mContext: Context

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = p0.context
        if (viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_home, p0, false)

            return HomeItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_loading,p0,false)

            return LoadingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeItemViewHolder) {
            holder.itemView.title.text = mDataList[position]
        } else if (holder is LoadingViewHolder) {
            holder.itemView.progressbar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (mDataList[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    fun setDataList(dataList: ArrayList<String?>) {
        mDataList = dataList
        notifyDataSetChanged()
    }

    fun addDataList(dataList: ArrayList<String?>) {
        mDataList.addAll(dataList)
        notifyDataSetChanged()
    }

    fun addData(data: String?) {
        mDataList.add(data)
        notifyDataSetChanged()
    }

    fun clearDataList() {
        mDataList.clear()
        notifyDataSetChanged()
    }

    fun onMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mDataList, i , i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mDataList, i , i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun swipe(position: Int, direction: Int) {
        mDataList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class HomeItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    }

    inner class LoadingViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)
}