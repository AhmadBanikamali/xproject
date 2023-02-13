package com.abcdandroid.presenter.datalist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abcdandroid.presenter.databinding.ItemRecyclerBinding

class PassengersAdapter :
    PagingDataAdapter<String, PassengersAdapter.ViewHolder>(PassengerComparator) {
    private lateinit var itemStateArray: List<String>
    private lateinit var onCheckChange: (data: String, isChecked: Boolean) -> Unit

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position) ?: "")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    fun setItemsArray(itemStateArray: List<String>) {
        this.itemStateArray = itemStateArray
        notifyDataSetChanged()
    }

    fun setOnCheckboxClick(onCheckChange: (data: String, isChecked: Boolean) -> Unit) {
        this.onCheckChange = onCheckChange
    }


    inner class ViewHolder(private val itemRecyclerBinding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(itemRecyclerBinding.root) {

        fun bind(data: String) {
            itemRecyclerBinding.apply {
                itemViewModel = ItemViewModel(data, itemStateArray.contains(data))
                cb.setOnCheckedChangeListener { buttonView, isChecked ->
                    onCheckChange(data, isChecked)
                    buttonView.isChecked = isChecked
                }
            }
        }
    }

    object PassengerComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }

}