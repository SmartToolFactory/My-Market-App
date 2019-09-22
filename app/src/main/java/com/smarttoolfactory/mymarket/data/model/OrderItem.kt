package com.smarttoolfactory.mymarket.data.model


/**
 * Order item that wrapper for [Order] to display orders in a RecyclerView Adapter
 * with option to show or conceal [ProductDetail]s
 */
class OrderItem(val order: Order) {

    var isDetailsVisible = false

}