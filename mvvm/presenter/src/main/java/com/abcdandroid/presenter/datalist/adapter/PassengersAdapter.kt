package com.abcdandroid.presenter.datalist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abcdandroid.presenter.databinding.ItemRecyclerBinding
import javax.inject.Inject

class PassengersAdapter @Inject constructor() :
    PagingDataAdapter<String, PassengersAdapter.ViewHolder>(PassengerComparator) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: "null")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context,),parent,false))
    }


    inner class ViewHolder(private val itemRecyclerBinding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemRecyclerBinding.root) {
        fun bind(name: String) {
            itemRecyclerBinding.name = name
        }
    }

    object PassengerComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }

}