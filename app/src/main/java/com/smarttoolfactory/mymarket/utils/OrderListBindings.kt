package com.smarttoolfactory.mymarket.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.view.adapter.OrderListAdapter


/*
    *** Bindings for List ***
 */
/**
 * [BindingAdapter]s for the [Movie]s to ListAdapter.
 */
@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items: List<OrderListItem>?) {
   items?.let {
       (listView.adapter as OrderListAdapter).submitList(items)
   }
}

