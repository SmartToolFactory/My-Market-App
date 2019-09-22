package com.smarttoolfactory.mymarket.utils

import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smarttoolfactory.mymarket.constants.*
import com.smarttoolfactory.mymarket.data.model.OrderListItem
import com.smarttoolfactory.mymarket.view.adapter.OrderListAdapter

/*
    *** Bindings for RecyclerView ***
 */

/**
 * [BindingAdapter]s for the [OrderListItem]s to ListAdapter.
 */
@BindingAdapter("app:items")
fun RecyclerView.setItems(items: List<OrderListItem>?) {
    items?.let {
        (adapter as OrderListAdapter)?.submitList(items)
    }
}


@BindingAdapter("app:expand")
fun RecyclerView.setExpandedItem(item: Int) {
    adapter?.notifyItemChanged(item)
}

/*
    *** Bindings for View ***
 */
/**
 * [BindingAdapter]s for the [OrderListItem]s to set state of rectangle view.
 */
@BindingAdapter("app:state")
fun View.setState(state: String) {

    val colorString = getColor(state)

    setBackgroundColor(Color.parseColor(colorString))
}

/*
    *** Bindings for AppCompatTextView ***
 */
/**
 * [BindingAdapter]s for the [OrderListItem]s to set state text.
 */
@BindingAdapter("app:stateColor")
fun AppCompatTextView.setStateColor(state: String) {

    val colorString = getColor(state)

    setTextColor(Color.parseColor(colorString))
}

/**
 * Get color depending on state of Order
 */
private fun getColor(state: String): String {
    return when (state) {
        STATE_WAITING_CONFIRMATION -> COLOR_RED
        STATE_BEING_PREPARED -> COLOR_YELLOW
        else -> COLOR_GREEN
    }
}



