package com.smarttoolfactory.mymarket.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.mymarket.BR
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.databinding.ItemOrderBinding
import com.smarttoolfactory.mymarket.ui.orders.OrdersViewModel

/**
 * Adapter for the order list. Has a reference to the [OrdersViewModel] to send actions back to it.
 *
 * Layout of the rows is determined by type of the binding class. For this adapter it's [ItemOrderBinding]
 * which is retrieved from item_order.xml
 */
class OrderListAdapter(private val viewModel: OrdersViewModel) :
    ListAdapter<OrderListItem, OrderListAdapter.CustomViewHolder<OrderListItem>>(TaskDiffCallback()) {

    var itemList = listOf<OrderListItem>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder<OrderListItem> {
        return CustomViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CustomViewHolder<OrderListItem>, position: Int) {

        val item = getItem(position)

        holder.bind(viewModel, item) { from, to ->
            notifyItemMoved(from, to)
        }


    }

    override fun onCurrentListChanged(
        previousList: MutableList<OrderListItem>,
        currentList: MutableList<OrderListItem>
    ) {
        super.onCurrentListChanged(previousList, currentList)

        itemList = currentList

    }


    // 🔥🔥🔥 item_order.xml is bounded as ItemOrderBinding
    class CustomViewHolder<T> private constructor(val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            viewModel: OrdersViewModel,
            item: T,
            action: (from: Int, to: Int) -> Unit
        ) {


            binding.root.setOnClickListener {
                action(adapterPosition, 0)

            }

            binding.viewModel = viewModel
            // Sets the OrderItem of the layout of row of the Adapter
//            binding.orderItem = item as OrderListItem
            binding.setVariable(BR.orderItem, item)
            binding.executePendingBindings()


        }

        companion object {
            fun from(parent: ViewGroup): CustomViewHolder<OrderListItem> {
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
