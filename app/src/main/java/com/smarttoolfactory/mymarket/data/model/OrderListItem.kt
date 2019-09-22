package com.smarttoolfactory.mymarket.data.model


/**
 * Order item that wrapper for [Order] list that is create at same date to display orders in a RecyclerView Adapter
 * with option to show or conceal [ProductDetail]s
 */
class OrderListItem(val orderList: List<Order>

) {

    var date: String? = orderList.first().date
    var month: String? = orderList.first().month
    var order: Order? = orderList?.first()

}