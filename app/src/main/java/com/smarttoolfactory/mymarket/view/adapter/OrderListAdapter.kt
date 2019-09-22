package com.smarttoolfactory.mymarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.databinding.ItemOrderBinding
import com.smarttoolfactory.mymarket.ui.orders.OrdersViewModel

/**
 * Adapter for the movie list. Has a reference to the [OrdersViewModel] to send actions back to it.
 */
class OrderListAdapter(private val viewModel: OrdersViewModel) :
    ListAdapter<OrderListItem, OrderListAdapter.CustomViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }


    class CustomViewHolder private constructor(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: OrdersViewModel, item: OrderListItem) {

            binding.viewModel = viewModel
            binding.orderItem = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOrderBinding.inflate(layoutInflater, parent, false)

                return CustomViewHolder(binding)
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class TaskDiffCallback : DiffUtil.ItemCallback<OrderListItem>() {
    override fun areItemsTheSame(oldItem: OrderListItem, newItem: OrderListItem): Boolean {
        return oldItem.order?.id == newItem.order?.id
    }

    override fun areContentsTheSame(oldItem: OrderListItem, newItem: OrderListItem): Boolean {
        return oldItem.order == newItem.order
    }
}
